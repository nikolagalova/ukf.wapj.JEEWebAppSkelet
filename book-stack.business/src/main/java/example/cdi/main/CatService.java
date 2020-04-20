package example.cdi.main;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import example.cdi.enums.COLOR;
import example.cdi.interfaces.FirstNameGenerator;
import example.cdi.interfaces.SurnameGenerator;
import example.cdi.model.Cat;
import example.cdi.qualifiers.ScientistName;
import example.cdi.util.RandomsUtil;

@Stateless
public class CatService {
	
	@Inject
	private Logger logger;
	
	@Inject @ScientistName
	private SurnameGenerator surnameGenerator;
	
	@Inject @Default
	private FirstNameGenerator firstNameGenerator;
	
	@Inject @ScientistName
	private String title;
	
	private Date instanciationDate;
	
	@PostConstruct
	private void initDate() {
		instanciationDate = new Date();
	}
		
	public Cat throwSomeRandomCat() {
		Cat cat = new Cat();
		cat.setColor(COLOR.getRandomColor());
		cat.setWeight(RandomsUtil.getRandomNumber(10));
		cat.setInstanciationDate(instanciationDate);
		String firstName = firstNameGenerator.generateName();
		String surname = surnameGenerator.generateSurname();
		cat.setName(title + " " +firstName+" "+surname);
		logger.info("Thrown cat: "+cat.getName());
		return cat;
	}
}
