package projectSI.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import projectSI.persistence.dto.PersonComparisionDTO;
import projectSI.persistence.dto.PersonPublicationChartDTO;
import projectSI.persistence.entities.Person;
import projectSI.persistence.entities.PubPublikacie;

@Stateless
public class PersonDAO extends AbstractDAO<Person>{

	@PersistenceContext(unitName = "uniStatsUnit")
	private EntityManager em;
	
	public PersonDAO() {
		super(Person.class);
	}
	
	public PersonDAO(Class<Person> entityClass) {
		super(entityClass);
	}
	
	public List<PubPublikacie> getPersonPublications(int ldapId){
		TypedQuery<PubPublikacie> typedQuery = em.createNamedQuery("Person.findPublications", PubPublikacie.class);
		typedQuery.setParameter("ldapId", ldapId);
		List<PubPublikacie> result = typedQuery.getResultList();
		return result;
	}
	
	public List<PersonPublicationChartDTO> getPersonPublicationData(int ldapId, int beginYear, int endYear){
		TypedQuery<PersonPublicationChartDTO> typedQuery = em.createNamedQuery("Person.findPublicationsInYears", PersonPublicationChartDTO.class);
		typedQuery.setParameter("ldapId", ldapId);
		typedQuery.setParameter("beginYear", beginYear);
		typedQuery.setParameter("endYear", endYear);
		List<PersonPublicationChartDTO> result = typedQuery.getResultList();
		return result;
	}
	
	public List<PubPublikacie> getDepartmentPublications(int departmentCode) {
		TypedQuery<PubPublikacie> typedQuery = em.createNamedQuery("Person.findDepartmentPublications", PubPublikacie.class);
		typedQuery.setParameter("departmentCode", departmentCode);
		List<PubPublikacie> result = typedQuery.getResultList();
		return result;
	}
	
	/**
	 * Method expects that parameter ldapId is unique, 
	 * therefore query will always return a single result
	 * @param ldapId
	 * @return Person with given ldapId, null if no such Person exists
	 */
	public Person getPersonByLadapId(int ldapId) {
		TypedQuery<Person> typedQuery = em.createNamedQuery("Person.findByLdapId", Person.class);
		typedQuery.setParameter("ldapId", ldapId);
		try {
			return typedQuery.getSingleResult();
		}
		catch(NoResultException ex){
			System.out.println("No result exepsion - returnning null.");
			return null;
		}
	}
	
	public List<PersonComparisionDTO> getDepartmentEmploees(int departmentCode, int headOfDepLdap) {
		TypedQuery<PersonComparisionDTO> typedQuery = em.createNamedQuery("Person.findAllEmployeesOfDepartment", PersonComparisionDTO.class);
		typedQuery.setParameter("departmentCode", departmentCode);
		typedQuery.setParameter("ldapId", headOfDepLdap);
		try {
			List<PersonComparisionDTO> result = typedQuery.getResultList();
			return result;
		}
		catch(NoResultException ex){
			System.out.println("No result exepsion - returnning null.");
			return null;
		}
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
