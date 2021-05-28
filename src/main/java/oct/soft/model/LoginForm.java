package oct.soft.model;

import javax.validation.constraints.NotEmpty;

public class LoginForm {
	@NotEmpty(message = "Utilizator este obligatoriu !")
	private String username;
	@NotEmpty(message = "Parola este obligatorie !")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
