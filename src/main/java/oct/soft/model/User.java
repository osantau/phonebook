package oct.soft.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.validator.constraints.Length;

public class User {

	private Integer id;
	@NotNull(message = "Introduceti un username !")
	@NotEmpty(message = "Campul Nume este obligatoriu !")
	@Length(min = 3, max = 45, message = "Campul Nume trebuie sa fie intre 3 si  25 caractere !")
	private String username;
	@Email(message = "Formatul adresei de email nu este valid!")
	@NotNull(message = "Introduceti un email !")
	@NotEmpty(message = "Campul email este obligatoriu !")
	private String email;
	@NotNull(message = "Introduceti un nume !")
	@NotEmpty(message = "Campul Nume este obligatoriu !")
	@Length(min = 3, max = 45, message = "Campul Nume trebuie sa fie intre 3 si  45 caractere !")
	private String fname;
	@NotNull(message = "Introduceti un nume !")
	@NotEmpty(message = "Campul Prenume este obligatoriu !")
	@Length(min = 3, max = 45, message = "Campul Prenume trebuie sa fie intre 3 si  45 caractere !")
	private String lname;
	private int isadmin;
	@NotNull(message = "Introduceti o parola !")
	@NotEmpty(message = "Campul Parola este obligatoriu !")
	@Length(min = 3, max = 45, message = "Campul Parola trebuie sa fie intre 3 si  45 caractere !")
	private String password;

	private String decodedPassword;
	
	public User() {
		this.password="";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {	
		this.password = password;
	}

	public String getDecodedPassword() {
		byte[] b = Base64.decodeBase64(getPassword());
		this.decodedPassword = new String(b);
		return decodedPassword;
	}

	public void setDecodedPassword(String decodedPassword) {
		this.decodedPassword = decodedPassword;
		this.password = Base64.encodeBase64String(decodedPassword.getBytes());
		
	}

	public boolean isNew() {
		return this.id == null ? true : false;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", fname=" + fname + ", lname="
				+ lname + ", isadmin=" + isadmin + ", password=" + password + ", decodedPassword=" + getDecodedPassword()+"]";
	}
	
	
}
