package com.smrc.gateone.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GateAndMetricDetailsDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ProjectMetricDetailsDTO> metricDetails;
	private List<ProjectGateDetailsDTO> projectGateDetails;
	public List<ProjectMetricDetailsDTO> getMetricDetails() {
		return metricDetails;
	}
	public void setMetricDetails(List<ProjectMetricDetailsDTO> metricDetails) {
		this.metricDetails = metricDetails;
	}
	public List<ProjectGateDetailsDTO> getProjectGateDetails() {
		return projectGateDetails;
	}
	public void setProjectGateDetails(List<ProjectGateDetailsDTO> projectGateDetails) {
		this.projectGateDetails = projectGateDetails;
	}
	
   
}
