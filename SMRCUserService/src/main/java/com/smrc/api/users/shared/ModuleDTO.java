package com.smrc.api.users.shared;

import java.io.Serializable;

public class ModuleDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private int moduleId;

	private String moduleCode;
		
	private String moduleName;
	
	private String moduleDesc;
	

	public ModuleDTO(int moduleId, String moduleCode, String moduleName, String moduleDesc) {
		super();
		this.moduleId = moduleId;
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.moduleDesc = moduleDesc;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	
	
	
}
