package com.smrc.api.users.ui.model;

public class LoginRequestModel {
	
	//private String email;
	private String winUserID;
	private String password;

	/*
	 * public String getEmail() { return email; } public void setEmail(String email)
	 * { this.email = email; }
	 */
	
	
	public String getPassword() {
		return password;
	}
	public String getWinUserID() {
		return winUserID;
	}
	public void setWinUserID(String winUserID) {
		this.winUserID = winUserID;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
