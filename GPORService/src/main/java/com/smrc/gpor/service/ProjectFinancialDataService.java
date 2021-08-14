package com.smrc.gpor.service;

import java.util.List;

import com.smrc.gpor.model.IQFVersion;
import com.smrc.gpor.model.ProjectFinancialResDTO;

public interface ProjectFinancialDataService {

	public List<ProjectFinancialResDTO> getProjectFinancialData(String projectId, String reportedMonth);
	
	
	public void createProjectFinancialData(String projectId, String reportedMonth,
			List<ProjectFinancialResDTO> projectFinancialResDTOList);
	
	public List<IQFVersion> getIQFVersionList();

}
