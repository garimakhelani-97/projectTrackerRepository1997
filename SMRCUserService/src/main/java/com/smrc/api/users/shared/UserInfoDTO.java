package com.smrc.api.users.shared;

import java.io.Serializable;

public class UserInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String winUserID;
	
	private String password;

	private int id;
	
	private String displayName;

	/*
	 * public String getWindowUserId() { return windowUserId; }
	 * 
	 * public void setWindowUserId(String windowUserId) { this.windowUserId =
	 * windowUserId; }
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	
}
