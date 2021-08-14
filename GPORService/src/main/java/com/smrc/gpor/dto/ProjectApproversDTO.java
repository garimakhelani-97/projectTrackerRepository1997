package com.smrc.gpor.dto;

public class ProjectApproversDTO {
	private Integer projectId;
	private Integer roleId;
	private String roleName;
	private Integer approvedBy;
	private Integer approvedStatus;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Integer approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Integer getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(Integer approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

}
