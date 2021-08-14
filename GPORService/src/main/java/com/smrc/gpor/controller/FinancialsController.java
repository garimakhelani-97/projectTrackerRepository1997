package com.smrc.gpor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.model.IQFVersion;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.model.ProjectFinancialResDTO;
import com.smrc.gpor.repository.ProjectApproverRepository;
import com.smrc.gpor.service.ProjectFinancialDataServiceImpl;

@RestController
public class FinancialsController {

	@Autowired
	ProjectFinancialDataServiceImpl projectFinancialDataServiceImpl;
	@Autowired
	private ProjectApproverRepository projectApproverRepository;
	@GetMapping("/projectFinancialData")
	public List<ProjectFinancialResDTO> getProjectFinancialDatacopy(@RequestParam String projectId,
			@RequestParam String reportedMonth) {

		List<ProjectFinancialResDTO> projectFinancialDataList = projectFinancialDataServiceImpl
				.getProjectFinancialData(projectId, reportedMonth);

		return projectFinancialDataList;

	}
	
	@GetMapping("/ApproveStatusMonthWise")
	public List<ProjectApprover> getApproveStatusMonthWise(@RequestParam String projectId,
			@RequestParam String reportedMonth) {
		List<ProjectApprover> MonthApproveData= projectApproverRepository.lastMonthApprovedStatus(Integer.parseInt(projectId), reportedMonth.split(" ")[0].substring(0,3), reportedMonth.split(" ")[1]);
		return MonthApproveData;

	}
	@PostMapping("/createFinancialData")
	public void createFinancialData(@RequestParam String projectId, @RequestParam String reportedMonth,
			@RequestBody List<ProjectFinancialResDTO> projectFinancialDTOList) {
		
		projectFinancialDataServiceImpl.createProjectFinancialData(projectId, reportedMonth, projectFinancialDTOList);

	}
	
	@GetMapping("/getIQFVersions")
	public List<IQFVersion> getIQFVersionList() {
		List<IQFVersion> iqfVersionList = projectFinancialDataServiceImpl.getIQFVersionList();
		return iqfVersionList;
	}

}
