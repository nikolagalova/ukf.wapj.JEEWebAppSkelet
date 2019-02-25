package projectSI.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import projectSI.persistence.entities.PubEpcSkupiny;

@Stateless
public class CategoryDAO extends AbstractDAO<PubEpcSkupiny>{
	
	@PersistenceContext(unitName = "uniStatsUnit")
	private EntityManager em;

	public CategoryDAO() {
		super(PubEpcSkupiny.class);
	}
	
	public CategoryDAO(Class<PubEpcSkupiny> entityClass) {
		super(entityClass);
	}
	
	public List<PubEpcSkupiny> getAllCategories(){
		TypedQuery<PubEpcSkupiny> typedQuery = em.createNamedQuery("Skupina.findAll", PubEpcSkupiny.class);
		return typedQuery.getResultList();
	}
	

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
