package com.smrc.gpor.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectSubmissionDTO {
	
	private Integer projectId;	
	private String month;
	private String year;	
	private String actionKey;
	private String comments;
	private Integer submissionFlag;
	private String updatedDate;
	private String submittedDate;
	
}
