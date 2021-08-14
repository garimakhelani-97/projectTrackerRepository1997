package com.smrc.gpor.dto;

import java.util.List;

public class WorkflowApproverAndTransectionDTO {

	List<WorkflowDetailsDTO> workflowDetails;
	List<WorkflowTransectionDetailDTO> transectionDetails;
	
	public List<WorkflowDetailsDTO> getWorkflowDetails() {
		return workflowDetails;
	}
	public void setWorkflowDetails(List<WorkflowDetailsDTO> workflowDetails) {
		this.workflowDetails = workflowDetails;
	}
	public List<WorkflowTransectionDetailDTO> getTransectionDetails() {
		return transectionDetails;
	}
	public void setTransectionDetails(List<WorkflowTransectionDetailDTO> transectionDetails) {
		this.transectionDetails = transectionDetails;
	}
	

}
