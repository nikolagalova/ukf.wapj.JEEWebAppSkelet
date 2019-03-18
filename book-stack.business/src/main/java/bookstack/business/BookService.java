package bookstack.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import bookstack.persistence.dao.AuthorDAO;
import bookstack.persistence.dao.BookDAO;
import bookstack.persistence.entities.Author;
import bookstack.persistence.entities.Book;

@Stateless
public class BookService {
	
	@Inject
	private BookDAO bookDao;
	
	@Inject
	private AuthorDAO authorDao;
	
	public List<Book> getBooksByTitle(String title) {
		return bookDao.getBooksByTitle(title);
	}
	
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}
	
	public Book create(Book book,Author author){
		Author authorFromDb = authorDao.getAuthorByName(author.getName(),author.getSurname());
		if(authorFromDb != null){
			book.setAutor(authorFromDb);
		}else{
			//neexistuje vytvorime noveho
			book.setAutor(author);
		}
		
		return this.bookDao.create(book);
	}

}
