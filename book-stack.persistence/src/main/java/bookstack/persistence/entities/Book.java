package bookstack.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name = "findBooksByTitle", query = "SELECT b FROM Book b WHERE b.title = :title")
})
public class Book implements Serializable {

	private static final long serialVersionUID = -7759431903190558099L;
	
	@Id @GeneratedValue
	private Integer id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "isbn")
	private String isbn;
	
	@Transient
	private String autor;
	
	public Book() {}
	
	/*
	 * Getters and setters
	 */
}
