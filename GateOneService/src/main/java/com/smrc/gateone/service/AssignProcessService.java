package com.smrc.gateone.service;

import java.util.Map;

import com.smrc.gateone.dto.ProcessAssignMapper;
import com.smrc.gateone.model.ProjectProcessAssociationModel;

public interface AssignProcessService {
 public ProjectProcessAssociationModel getProcessAssociationData(String projectId); 
 
 public String saveProcessAssociationData(String projectId,Map processAssignMapper);
 
}
