package com.smrc.gpor.model;

import java.util.List;
import java.util.Map;

public class SavePipData {

	private String projectId;
	private List<PipAction> pipActions;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Object getPipActions() {
		return pipActions;
	}

	public void setPipActions(List pipActions) {
		this.pipActions = pipActions;
	}
	
	
	
}
