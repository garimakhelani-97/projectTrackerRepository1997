package com.smrc.gpor.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.dto.ProjectSubmissionDTO;
import com.smrc.gpor.service.ProjectSubmissionService;


@RestController
public class ProjectSubmissionController {

	@Autowired
	ProjectSubmissionService projectFinancialDataService;


	@PostMapping("submitProject")
	public ResponseEntity<String> submitProject(@RequestBody ProjectSubmissionDTO projectSubmissionDTO,HttpServletRequest req ) throws MessagingException {
		String userId=req.getHeader("userid");
		projectFinancialDataService.submitProject(projectSubmissionDTO,userId);
		return ResponseEntity.ok(null);
		
	}
	
	
	

}
