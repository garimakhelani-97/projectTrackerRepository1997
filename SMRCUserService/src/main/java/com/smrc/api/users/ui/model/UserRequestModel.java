package com.smrc.api.users.ui.model;

import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestModel {

	@NotNull(message = "First Name cannot be null")
	@Size(min = 2, message = "First Name cannot be less then 2 characters")
	public String firstName;

	@NotNull(message = "Last Name cannot be null")
	@Size(min = 2, message = "Last Name cannot be less then 2 characters")
	public String LastName;

	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 16, message = "Password must qualify the required criteria")
	public String password;

	@NotNull(message = "Email cannot be null")
	@Email
	public String email;

	private Map<String, String> jsonData;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, String> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, String> jsonData) {
		this.jsonData = jsonData;
	}

}
