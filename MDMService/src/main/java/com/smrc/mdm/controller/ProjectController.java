package com.smrc.mdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.mdm.model.ProjectInputData;
import com.smrc.mdm.model.ProjectMapper;
import com.smrc.mdm.service.CreateProjectServiceImpl;
import com.smrc.mdm.service.ProjectListInputDataServiceImpl;
import com.smrc.mdm.service.SyncGPORServiceImlp;
import com.smrc.mdm.service.UpdateProjectServiceImpl;

@RestController
public class ProjectController {

	@Autowired
	private ProjectListInputDataServiceImpl projectListInputDataServiceImpl;

	@Autowired
	CreateProjectServiceImpl createProjectServiceImpl;

	@Autowired
	UpdateProjectServiceImpl updateProjectServiceImpl;

	@GetMapping("/projectListInputData")
	public ProjectInputData getProjectListInputData() {

		ProjectInputData projectInputData = projectListInputDataServiceImpl.getProjectListInputData();

		return projectInputData;

	}

	@PostMapping("/addProject")
	public ResponseEntity<String> createProject(@RequestBody ProjectMapper projectMapper) {

		String renponse = createProjectServiceImpl.createProject(projectMapper);
		return ResponseEntity.ok().body(renponse);

	}

	@PutMapping("/updateProject")
	public ResponseEntity<String> updateProject(@RequestBody ProjectMapper projectMapper) {

		updateProjectServiceImpl.updateProject(projectMapper);
		
		// URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

		return ResponseEntity.ok().build();

	}

}
