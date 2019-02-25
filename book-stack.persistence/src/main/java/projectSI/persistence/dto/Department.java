package projectSI.persistence.dto;

public class Department {

	private int kod;
	private String shortName;
	private String nazov;
		
	public Department(int kod, String shortName, String nazov) {
		super();
		this.kod = kod;
		this.shortName = shortName;
		this.nazov = nazov;
	}
	
	@Override
	public String toString() {
		return nazov;
	}

	public int getKod() {
		return kod;
	}
	public void setKod(int kod) {
		this.kod = kod;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getNazov() {
		return nazov;
	}
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
}
