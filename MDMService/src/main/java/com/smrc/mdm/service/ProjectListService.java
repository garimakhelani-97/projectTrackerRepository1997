package com.smrc.mdm.service;

import org.springframework.data.domain.Page;

import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectByIdMapper;
import com.smrc.mdm.model.ProjectListMapper;

public interface ProjectListService {
	
	public Page<ProjectListMapper> getAllProjectList();
	
	public ProjectByIdMapper getProjectById(Integer projectId);
	
	public Project getProjectByProjectId(Integer projectId);

}
