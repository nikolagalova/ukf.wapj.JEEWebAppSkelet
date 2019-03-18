package bookstack.ui.controller;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import bookstack.business.AuthorService;
import bookstack.business.BookService;
import bookstack.persistence.dao.BookDAO;
import bookstack.persistence.entities.Author;
import bookstack.persistence.entities.Book;

//anotacia, ktorou vieme jednoznacne identifikovat nazov beany v kontajneri ak je
//pouzita bez parametra : @Named pouzije sa ako identifikator nazov beany (bookController)
@Named(value="booksController")
// Request Scope - Bean sa vytvori pri kazdom HTTP requeste a zanikne hned po ukonceni
@RequestScoped
public class BookController implements Serializable{
// kazdy objekt, ktory chcete pouzit aj na UI na xhtml-ku musi implementovat triedu Serializable !!!
	/**
	 * 
	 */
	private static final long serialVersionUID = 3537455957738848730L;
	private static final int ISBN_LENGTH = 13;
	private static final String[] randomNames = {"Cristiano","Eden","Marek","Cesc","Kylian","Karim","Paul"};
	private static final String[] randomSurnames = {"Ronaldo", "Hazard", "Hamsik", "Fabregas","Mbappe","Benzema","Pogba"};

	private static final String[] books = {"Harry Potter","Šlabikár","Mengeleho dievca","Treblinka","Teoria pressingu","Clean code","Agile Software development handbook"};
	
	@Inject
	private BookService bookService;
	
	// ak v čase volanie BookController nie je zobrazena stranka na ktorej bude tato bean, tak bude nastavena na NULL
	@Inject 
	private BooksView booksView;
	
	@Inject 
	private BookSessionStatistics bookSessionStatistics;
	
	@PostConstruct
	private void init(){
		System.out.println(this.getClass().getName() + " was created.");

	}
	
	@PreDestroy
	private void destroy(){
		System.out.println(this.getClass().getName() + " was destroyed.");
	}
	
	/**
	 * Method for returning a random name from the static array of names {@link randomNames}
	 * @return random name from the names array
	 */
	private String getRandomName(){
		return randomNames[ThreadLocalRandom.current().nextInt(0,randomNames.length)];
	}
	
	/**
	 * JAVADOC - dokumentacia k java kodu staci napisat /** stlacit enter
	 * a vygeneruje sa Vam vzor ktory mozete upravit
	 * @return nahodne meno zo statickeho pola mien
	 */
	private String getRandomSurname(){
		return randomSurnames[ThreadLocalRandom.current().nextInt(0,randomSurnames.length)];
	}
	
	private String generateIsbn(){
		Integer value1 = ThreadLocalRandom.current().nextInt(0,Integer.MAX_VALUE);
		StringBuilder builder = new StringBuilder(value1.toString());
		while(builder.toString().length() != ISBN_LENGTH){
			if(builder.toString().length() > ISBN_LENGTH){
			   builder = builder.deleteCharAt(0);
			}else{
				builder.append("0");
			}
		}
		return builder.toString();
	}
	
	public void createBook(){
		String name = getRandomName();
		String surname = getRandomSurname();

		Author author = new Author(name, surname);
		
		Book book = new Book();
		book.setTitle(books[ThreadLocalRandom.current().nextInt(0,books.length)]);
		book.setIsbn(generateIsbn());
		
		//Nastavim vazbu na autora
		//book.setAutor(author);
		//author.getBooks().add(book);
		//IBA TOTO VOLANIE SA VYKONAVA V TRANSAKCII CDI NEMA PODPORU PRE TRANSAKCIE EJB ANO
		bookService.create(book,author);
		
		//refresh data na UI
		booksView.setBookList(bookService.getAllBooks());
		bookSessionStatistics.triggerRandomBookCreationStatistics();
	}

}
