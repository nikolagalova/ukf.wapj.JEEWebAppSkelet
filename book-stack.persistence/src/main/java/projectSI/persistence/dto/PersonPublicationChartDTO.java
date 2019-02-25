package projectSI.persistence.dto;

import java.math.BigDecimal;

import projectSI.persistence.entities.PubEpcSkupiny;

public class PersonPublicationChartDTO {
	
	private PubEpcSkupiny pubCategory;
	private int year;
	private int count;
	private double podiel;
	
	public PersonPublicationChartDTO(PubEpcSkupiny pubCategory, int year,
			Long count, BigDecimal podiel) {
		this.pubCategory = pubCategory;
		this.year = year;
		this.count = count.intValue();
		this.podiel = podiel.doubleValue();
	}
	
	public PersonPublicationChartDTO(int year, Long count, BigDecimal podiel) {
		this.year = year;
		this.count = count.intValue();
		this.podiel = podiel.doubleValue();
	}
	
	
	/* GETTERS & SETTERS:*/
	public PubEpcSkupiny getPubCategory() {
		return pubCategory;
	}
	public void setPubCategory(PubEpcSkupiny pubCategory) {
		this.pubCategory = pubCategory;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
