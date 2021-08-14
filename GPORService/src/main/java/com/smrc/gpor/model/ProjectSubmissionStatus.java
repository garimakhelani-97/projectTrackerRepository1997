package com.smrc.gpor.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "project_submission_status")
@Data
public class ProjectSubmissionStatus implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer projectId;
	private String month;
	private String year;
	private Integer submissionFlag;
	private Integer submittedBy;
	private String submittedDate;
	private String ipAddress;
	private Integer updatedBy;
	private String updatedDate;
	

}
