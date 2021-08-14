package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workflow_detail")	
public class WorkflowDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer workflowId;
	private Integer roleId;
	private String roleName;
	private Integer orderId;
	private Integer isMandatory;
	private String remark;
	private String ipAddress;
	private Integer createdBy;
	private String createdDate;
	private Integer updatedBy;
	private String updatedDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(Integer workflowId) {
		this.workflowId = workflowId;
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
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getIsMandatory() {
		return isMandatory;
	}
	public void setIsMandatory(Integer isMandatory) {
		this.isMandatory = isMandatory;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
