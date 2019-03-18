package bookstack.persistence.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import bookstack.persistence.entities.Author;

@Stateless
public class AuthorDAO extends AbstractDAO<Author> {

	public AuthorDAO() {
		super(Author.class);
	}

	/**
	 * Method retrieves the author which is identified by name and surname
	 * (unique), in case of no author found it returns NULL OBJECT.
	 * 
	 * @param name
	 * @param surname
	 * @return author identified by name and surname, otherwise NULL
	 */
	public Author getAuthorByName(String name, String surname) {
		TypedQuery<Author> query = em.createNamedQuery("findBooksByNames", Author.class);
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			// in case of no result return null
			return null;
		}

	}

}
