package example.cdi.model;

import java.util.Date;

import example.cdi.enums.COLOR;

public class Cat {
	
	private String name;
	private int weight;
	private COLOR color;
	private Date instanciationDate;
	
	public Cat() {	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public COLOR getColor() {
		return color;
	}

	public void setColor(COLOR color) {
		this.color = color;
	}

	public Date getInstanciationDate() {
		return instanciationDate;
	}

	public void setInstanciationDate(Date instanciationDate) {
		this.instanciationDate = instanciationDate;
	}

}
