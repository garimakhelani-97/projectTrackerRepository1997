package com.smrc.mdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectByIdMapper;
import com.smrc.mdm.model.ProjectListMapper;
import com.smrc.mdm.service.ProjectListServiceImpl;

@RestController

public class ProjectListController {

	@Autowired
	private ProjectListServiceImpl projectListServiceImpl;

	@GetMapping("/projectList")
	public Page<ProjectListMapper> getAllProjectList() {

		return projectListServiceImpl.getAllProjectList();

	}

	@GetMapping("/projectList/{id}")
	public ProjectByIdMapper getProjectById(@PathVariable String id) {

		Integer projectId = Integer.parseInt(id);
		ProjectByIdMapper projectByIdMapper = projectListServiceImpl.getProjectById(projectId);

		//return ResponseEntity.ok().body(project);
		return projectByIdMapper;
	}

	}