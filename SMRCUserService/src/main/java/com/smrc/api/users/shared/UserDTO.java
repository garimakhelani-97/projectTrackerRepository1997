package com.smrc.api.users.shared;

import java.io.Serializable;
import java.util.Map;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 8107705830657465758L;

	private long id;

	private String firstName;

	private String LastName;

	private String password;

	private String email;

	private String userId;

	private String encryptedPassword;

	// private JSONObject jsonData;
	private Map<String, String> jsonData;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public Map<String, String> getJsonData() {
		return jsonData;
	}

	public void setJsonData(Map<String, String> jsonData) {
		this.jsonData = jsonData;
	}

	/*
	 * public JSONObject getJsonData() { return jsonData; }
	 * 
	 * public void setJsonData(JSONObject jsonData) { this.jsonData = jsonData; }
	 */

}
