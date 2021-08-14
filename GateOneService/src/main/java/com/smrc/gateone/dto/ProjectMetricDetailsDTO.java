package com.smrc.gateone.dto;

import java.io.Serializable;

public class ProjectMetricDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer projectId;
	private String name;
	private String description;
	private Integer metricsId;
	private String  comment;
	private Integer metricPKId;
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getMetricsId() {
		return metricsId;
	}
	public void setMetricsId(Integer metricsId) {
		this.metricsId = metricsId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getMetricPKId() {
		return metricPKId;
	}
	public void setMetricPKId(Integer metricPKId) {
		this.metricPKId = metricPKId;
	}
	
	
}
