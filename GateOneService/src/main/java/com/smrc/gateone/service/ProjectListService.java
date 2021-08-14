package com.smrc.gateone.service;

import java.util.List;

import com.smrc.gateone.dto.ProjectListDTO;
import com.smrc.gateone.model.Gate1ProjectProcessAssociation;
import com.smrc.gateone.model.Project;

public interface ProjectListService {

	public List<ProjectListDTO> getProjectList();

	public List<Gate1ProjectProcessAssociation> getProjectProcessAssociation(Integer projectId);
}
