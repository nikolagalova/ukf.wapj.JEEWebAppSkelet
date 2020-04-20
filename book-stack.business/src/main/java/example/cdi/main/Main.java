package example.cdi.main;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;

import example.cdi.model.Cat;

@Singleton
@Startup
public class Main {
	
	@Inject
	private CatService catService;
	
	@PostConstruct
	private void main() {
		Cat cat = catService.throwSomeRandomCat();
		System.out.println("I have thrown at you this cat: " + cat.getName());
	}

}
