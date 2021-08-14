package com.smrc.gateone.dto;

import java.io.Serializable;
import java.util.Date;

public class ProjectGateDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer gateId;
	private Integer projectId;
	private Integer gatePKId;
	private Date initialDate;
	private Integer isFreezed;
	public Integer getGateId() {
		return gateId;
	}
	public void setGateId(Integer gateId) {
		this.gateId = gateId;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getGatePKId() {
		return gatePKId;
	}
	public void setGatePKId(Integer gatePKId) {
		this.gatePKId = gatePKId;
	}
	public Date getInitialDate() {
		return initialDate;
	}
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}
	public Integer getIsFreezed() {
		return isFreezed;
	}
	public void setIsFreezed(Integer isFreezed) {
		this.isFreezed = isFreezed;
	}
	
	

}
