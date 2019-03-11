package bookstack.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import bookstack.persistence.dao.BookDAO;
import bookstack.persistence.entities.Book;

@Stateless
public class BookService {
	
	@Inject
	private BookDAO bookDao;
	
	public List<Book> getBooksByTitle(String title) {
		return bookDao.getBooksByTitle(title);
	}
	
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}

}
