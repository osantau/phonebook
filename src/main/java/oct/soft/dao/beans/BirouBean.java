package oct.soft.dao.beans;

public class BirouBean {
	private String birou;
	private String numere;

	public BirouBean() {
		this.birou = "";
		this.numere = "";
	}

	public String getBirou() {
		return birou;
	}

	public void setBirou(String birou) {
		this.birou = birou;
	}

	public String getNumere() {
		return numere;
	}

	public void setNumere(String numere) {
		this.numere = numere;
	}

	@Override
	public String toString() {
		return "BirouBean [birou=" + birou + ", numere=" + numere + "]\n";
	}

}
