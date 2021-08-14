package com.smrc.gpor.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workflow_approver")
public class WorkFlowApprover implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer unitId;
	private Integer projectId;
	private Integer roleId;
	private Integer order;
	private Integer approverId;
	private Integer approveStatus;
	private Integer workflowDetailId;
	private Integer workflowSubmissionId;
	private Date approvedDate;
	private Date receivedDate;
	private String comments;
	private Integer reminderCount;
	private Date lastReminder;

	public WorkFlowApprover() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getWorkflowDetailId() {
		return workflowDetailId;
	}

	public void setWorkflowDetailId(Integer workflowDetailId) {
		this.workflowDetailId = workflowDetailId;
	}

	public Integer getWorkflowSubmissionId() {
		return workflowSubmissionId;
	}

	public void setWorkflowSubmissionId(Integer workflowSubmissionId) {
		this.workflowSubmissionId = workflowSubmissionId;
	}

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getReminderCount() {
		return reminderCount;
	}

	public void setReminderCount(Integer reminderCount) {
		this.reminderCount = reminderCount;
	}

	public Date getLastReminder() {
		return lastReminder;
	}

	public void setLastReminder(Date lastReminder) {
		this.lastReminder = lastReminder;
	}

}
