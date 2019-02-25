package projectSI.persistence.dto;

import java.math.BigDecimal;

import projectSI.persistence.entities.Person;

public class PersonComparisionDTO {
	
	private Person person;
	private int count;
	private double podiel;
	
	public PersonComparisionDTO(Person person, Long count, BigDecimal podiel) {
		super();
		this.person = person;
		this.count = count.intValue();
		this.podiel = podiel.doubleValue();
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getPodiel() {
		return podiel;
	}
	
	public void setPodiel(double podiel) {
		this.podiel = podiel;
	}
	
}
