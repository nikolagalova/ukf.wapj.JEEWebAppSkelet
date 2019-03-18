package bookstack.ui.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookStoreStatistics {

	
	@PostConstruct
	public void init(){
		System.out.println("Application started at: "+ getDateString());
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("Application shutting down at: "+ getDateString());
	}
	
	private String getDateString(){
		SimpleDateFormat dt = new SimpleDateFormat("dd.mm.yyyy hh:mm:ss"); 
		return dt.format(new Date());
	}
	
}
