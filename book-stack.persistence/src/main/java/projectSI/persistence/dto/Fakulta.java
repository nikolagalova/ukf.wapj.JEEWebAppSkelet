package projectSI.persistence.dto;

public class Fakulta {
	
	private int kod;
	private String nazov;
	
	public Fakulta(int kod, String nazov) {
		super();
		this.kod = kod;
		this.nazov = nazov;
	}
	
	public int getKod() {
		return kod;
	}
	public void setKod(int kod) {
		this.kod = kod;
	}

	public String getNazov() {
		return nazov;
	}
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}

}
