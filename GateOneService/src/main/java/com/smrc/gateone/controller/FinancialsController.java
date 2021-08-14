package com.smrc.gateone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.dto.ProjectFinancialResDTO;
import com.smrc.gateone.serviceImpl.ProjectFinancialDataServiceImpl;

//import com.smrc.gpor.model.IQFVersion;
//import com.smrc.gpor.model.ProjectFinancialResDTO;
//import com.smrc.gpor.service.ProjectFinancialDataServiceImpl;

@RestController
@RequestMapping("/financials")
public class FinancialsController {

	@Autowired
	ProjectFinancialDataServiceImpl projectFinancialDataServiceImpl;

	@GetMapping("/projectFinancialData")
	public List<ProjectFinancialResDTO> getProjectFinancialDatacopy(@RequestParam String projectId) {

		List<ProjectFinancialResDTO> projectFinancialDataList = projectFinancialDataServiceImpl
				.getProjectFinancialData(projectId);

		return projectFinancialDataList;

	}
	
	@PostMapping("/createFinancialData")
	public void createFinancialData(@RequestParam String projectId,
			@RequestBody List<ProjectFinancialResDTO> projectFinancialDTOList) {
		
		projectFinancialDataServiceImpl.createProjectFinancialData(projectId, projectFinancialDTOList);

	}
//	
//	@GetMapping("/getIQFVersions")
//	public List<IQFVersion> getIQFVersionList() {
//		List<IQFVersion> iqfVersionList = projectFinancialDataServiceImpl.getIQFVersionList();
//		return iqfVersionList;
//	}

}
