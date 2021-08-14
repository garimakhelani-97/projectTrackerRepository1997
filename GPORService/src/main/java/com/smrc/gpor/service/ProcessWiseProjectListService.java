package com.smrc.gpor.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.smrc.gpor.dto.WorkflowApproverAndTransectionDTO;
import com.smrc.gpor.dto.WorkflowTransectionDetailDTO;
import com.smrc.gpor.model.Gate;
import com.smrc.gpor.model.ProcessWiseProject;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.Status;
import com.smrc.gpor.model.WorkFlowSubmission;

public interface ProcessWiseProjectListService {
	
	public Page<ProcessWiseProject> geProcessWiseProjectList(Integer processId, String reportedMonth,Integer userId);
	
	Gate getGate(String reportedMonth, Project project);
	
	Status getOverAllStatus(Integer projectId, Integer metricsId, String reportedMonth);
	
	WorkFlowSubmission getWorkFlow(Integer projectId, String reportedMonth);
	
	WorkflowApproverAndTransectionDTO getWorkFlowTransections(String projectId, String reportedDate);

}
