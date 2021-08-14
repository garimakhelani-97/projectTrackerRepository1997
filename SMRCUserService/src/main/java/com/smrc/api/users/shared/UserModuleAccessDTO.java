package com.smrc.api.users.shared;

import java.io.Serializable;

public class UserModuleAccessDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userInfoId;
	
	private int moduleId;

	public int getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	

	
	
	
}
