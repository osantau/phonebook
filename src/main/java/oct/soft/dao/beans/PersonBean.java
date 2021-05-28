package oct.soft.dao.beans;

public class PersonBean {

	public PersonBean() {
		
		this.nume = "";
		this.prenume = "";
		this.numere = "";
		this.numar_asociat = "";
	}
	private String nume;
	private String prenume;
	private String numere;
	private String numar_asociat;
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public String getNumere() {
		return numere;
	}
	public void setNumere(String numere) {
		this.numere = numere;
	}
	public String getNumar_asociat() {
		return numar_asociat;
	}
	public void setNumar_asociat(String numar_asociat) {
		this.numar_asociat = numar_asociat;
	}
	@Override
	public String toString() {
		return "PersonBean [nume=" + nume + ", prenume=" + prenume + ", numere=" + numere + ", numar_asociat="
				+ numar_asociat + "]\n";
	}
	
	
}
