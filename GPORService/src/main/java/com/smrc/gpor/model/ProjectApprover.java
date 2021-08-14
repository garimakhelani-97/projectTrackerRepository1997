package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "project_approvers")
@Data
public class ProjectApprover implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer projectId;
	
	private String month;
	private String year;
	
	private Integer roleId; 
	private String roleName; 
	private Integer orderId; 
	private Integer approvedBy;
	private Integer approveStatus;
	private Integer statusId;
	private String remark;
	private String ipAddress;
	private Integer createdBy;
	private String createdDate; 
	private Integer updatedBy; 
	private String updatedDate;
	private String receivedDate;
	private String holdFlag ;
	private String holdDate;
	private Integer reminderCount; 
	private String lastReminder;
	

}
