package bookstack.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bookstack.persistence.entities.Book;

@Stateless
public class BookDAO extends AbstractDAO<Book>{
	
	public BookDAO() {
		super(Book.class);
	}
	
	public List<Book> getBooksByTitle(String title) {
		TypedQuery<Book> query = em.createNamedQuery("findBooksByTitle", Book.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

}
