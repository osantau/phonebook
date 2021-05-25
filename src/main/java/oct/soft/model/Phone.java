package oct.soft.model;

public class Phone {
	private int idphone;
	private int number;
	private int fix;
	private int interior;
	private int mobil;
	private int serv;
	private int idoffice;
	private int idperson;

	public Phone() {

	}

	public int getIdphone() {
		return idphone;
	}

	public void setIdphone(int idphone) {
		this.idphone = idphone;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getFix() {
		return fix;
	}

	public void setFix(int fix) {
		this.fix = fix;
	}

	public int getInterior() {
		return interior;
	}

	public void setInterior(int interior) {
		this.interior = interior;
	}

	public int getMobil() {
		return mobil;
	}

	public void setMobil(int mobil) {
		this.mobil = mobil;
	}

	public int getServ() {
		return serv;
	}

	public void setServ(int serv) {
		this.serv = serv;
	}

	public int getIdoffice() {
		return idoffice;
	}

	public void setIdoffice(int idoffice) {
		this.idoffice = idoffice;
	}

	public int getIdperson() {
		return idperson;
	}

	public void setIdperson(int idperson) {
		this.idperson = idperson;
	}

	public String getTypeLabel() {
		if (getFix() == 1) {
			return "Fix";
		} else if (getMobil() == 1) {
			return "Mobil";
		} else if (getServ() == 1) {
			return "Serviciu";
		} else if (getInterior() == 1) {
			return "Interior";
		}
		return "";
	}

}
