package bookstack.ui.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;


@SessionScoped
public class BookSessionStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1448165106022714015L;

	private int numberOfRandomBooksCreatedPerSession;
	
	public void triggerRandomBookCreationStatistics(){
		numberOfRandomBooksCreatedPerSession++;
		System.out.println("Number of random books created in this session: "+ numberOfRandomBooksCreatedPerSession);
	}
	
	@PostConstruct
	private void init(){
		System.out.println(this.getClass().getName() + " was created.");

	}
	
	@PreDestroy
	private void destroy(){
		System.out.println(this.getClass().getName() + " was destroyed.");
	}
}
