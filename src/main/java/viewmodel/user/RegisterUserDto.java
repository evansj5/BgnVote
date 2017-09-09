package viewmodel.user;

import java.util.Optional;

public class RegisterUserDto {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String regKey;
	private Optional<String> bggUsername;
	
	public RegisterUserDto() {}

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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegKey() {
		return regKey;
	}

	public void setRegKey(String regKey) {
		this.regKey = regKey;
	}

	public Optional<String> getBggUsername() {
		return bggUsername;
	}

	public void setBggUsername(Optional<String> bggUsername) {
		this.bggUsername = bggUsername;
	}
	
	
	
}
