package bookstack.ui.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import bookstack.business.BookService;
import bookstack.persistence.entities.Author;
import bookstack.persistence.entities.Book;

@ViewScoped
@Named
public class BooksView implements Serializable {
	
	private static final long serialVersionUID = 6559129950166292602L;
	
	private String input;
	private Author author;
	private List<Book> bookList;
	
	@Inject
	private BookService bookService;
	
	@PostConstruct
	private void init() {
		System.out.println(this.getClass().getName() + " created.");
		bookList = bookService.getAllBooks();
		input = "init hodnota";
		author = new Author();
	}
	
	@PreDestroy
	private void destroy(){
		System.out.println(this.getClass().getName() + " was destroyed.");
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	

}
