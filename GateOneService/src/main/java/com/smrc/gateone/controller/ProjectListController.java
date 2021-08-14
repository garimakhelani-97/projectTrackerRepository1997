package com.smrc.gateone.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.dto.ProjectListDTO;
import com.smrc.gateone.model.Gate1ProjectProcessAssociation;
//import com.smrc.gpor.model.ProcessWiseProject;
import com.smrc.gateone.service.ProjectListService;

@RestController
@RequestMapping("/gateone")
public class ProjectListController {

	@Autowired 
	private ProjectListService projectListService;

	@GetMapping("/projectlist")
	public List<ProjectListDTO> getProjectList(HttpServletRequest req ) {
		System.out.println(req);
		String userId=req.getHeader("userid");
		System.out.println(userId);
		return projectListService.getProjectList();

	}
	
	@GetMapping("/getPPAByProjectId/{projectId}")
	public List<Gate1ProjectProcessAssociation> getProjectProcessAssociation(@PathVariable Integer projectId) {

		return projectListService.getProjectProcessAssociation(projectId);

	}
}
