package com.smrc.gpor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.dto.WorkflowApproverAndTransectionDTO;
import com.smrc.gpor.dto.WorkflowTransectionDetailDTO;
import com.smrc.gpor.model.ProcessWiseProject;
import com.smrc.gpor.service.ProcessWiseProjectListServiceImpl;

@RestController
public class ProcessWiseProjectListController {

	@Autowired
	private ProcessWiseProjectListServiceImpl processWiseProjectListServiceImpl;
    
	@GetMapping("/processWiseProjectList/{processId}")
	public Page<ProcessWiseProject> geProcessWiseProjectList(@PathVariable Integer processId,
			@RequestParam String reportedMonth,HttpServletRequest req ) {
		System.out.println(req);
		System.out.println(req.getHeader("userid"));
		Integer  userId=Integer.parseInt(req.getHeader("userid").toString());
		return processWiseProjectListServiceImpl.geProcessWiseProjectList(processId, reportedMonth,userId);

	}
//	Get workflow transections details
   @GetMapping("/getTransectionDataById")
   public WorkflowApproverAndTransectionDTO getWorkFlowTransectionDetails(@RequestParam String projectId,@RequestParam String reportingDate){
	  return processWiseProjectListServiceImpl.getWorkFlowTransections(projectId, reportingDate);
	  //  return  ResponseEntity.ok(null);
   }
}
