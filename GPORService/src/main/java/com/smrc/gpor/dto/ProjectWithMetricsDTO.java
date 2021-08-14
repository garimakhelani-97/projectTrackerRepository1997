package com.smrc.gpor.dto;



import lombok.Data;

public class ProjectWithMetricsDTO {
	private String name;
	private Integer projectId;
	private Integer metricsId;
	private Integer statusId;
	private String comment;
	private String reportedMonth;
//	private Date createdOn;
//	private Date updatedOn;
	private String className;
	private String description;
	private Integer id;
	private Integer projectMetricsTableId;
	//private String reportedMonth;
	
	

	public ProjectWithMetricsDTO() {
		
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Integer metricsId) {
		this.metricsId = metricsId;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReportedMonth() {
		return reportedMonth;
	}

	public void setReportedMonth(String reportedMonth) {
		this.reportedMonth = reportedMonth;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectMetricsTableId() {
		return projectMetricsTableId;
	}

	public void setProjectMetricsTableId(Integer projectMetricsTableId) {
		this.projectMetricsTableId = projectMetricsTableId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
