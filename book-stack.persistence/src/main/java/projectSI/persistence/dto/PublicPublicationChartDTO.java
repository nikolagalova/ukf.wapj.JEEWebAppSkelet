package projectSI.persistence.dto;

import java.math.BigDecimal;

public class PublicPublicationChartDTO {
	
	private int katedraKod;
	private int count;
	private double podiel;
	
	public PublicPublicationChartDTO(int katedraKod, Long count, BigDecimal podiel) {
		super();
		this.katedraKod = katedraKod;
		this.count = count.intValue();
		this.podiel = podiel.doubleValue();
	}

	public int getKatedraKod() {
		return katedraKod;
	}

	public void setKatedraKod(int katedraKod) {
		this.katedraKod = katedraKod;
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
