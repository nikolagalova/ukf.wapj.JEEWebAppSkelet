package projectSI.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import projectSI.persistence.dto.Department;
import projectSI.persistence.dto.Fakulta;
import projectSI.persistence.dto.PublicPublicationChartDTO;

@Stateless
public class FakultaDAO extends AbstractDAO<Fakulta> {

	@PersistenceContext(unitName = "uniStatsUnit")
	private EntityManager em;

	public FakultaDAO() {
		super(Fakulta.class);
	}

	public List<Fakulta> getAllFakulty() {
		TypedQuery<Fakulta> typedQuery = em.createNamedQuery(
				"Person.findAllFakulty", Fakulta.class);
		return typedQuery.getResultList();
	}

	public List<PublicPublicationChartDTO> getFakultyPublications(int fakultaKod) {
		TypedQuery<PublicPublicationChartDTO> typedQuery = em.createNamedQuery(
				"Person.findPublicationsOfFakulty",
				PublicPublicationChartDTO.class);
		typedQuery.setParameter("fakultaKod", fakultaKod);
		return typedQuery.getResultList();
	}
	
	public List<Department> getAllDepartments() {
		TypedQuery<Department> typedQuery = em.createNamedQuery(
				"Person.findAllDepartments", Department.class);
		return typedQuery.getResultList();
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
