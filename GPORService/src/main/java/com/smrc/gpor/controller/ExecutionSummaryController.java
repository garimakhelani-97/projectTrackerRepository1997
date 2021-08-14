package com.smrc.gpor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.dto.ExecutionSummaryDetailsDTO;
import com.smrc.gpor.serviceImpl.ExecutionSummaryServiceImpl;

@RestController
public class ExecutionSummaryController {
	@Autowired
	ExecutionSummaryServiceImpl executionSummaryServiceImpl;
	
	@GetMapping("/getExecutionSummaryByMonthAndYear")
	public List<ExecutionSummaryDetailsDTO> getExecutionSummary(@RequestParam String month,@RequestParam String year) {
		return executionSummaryServiceImpl.getExecutionDetails(month, year);
		
	}
	
	@PostMapping("/saveExecutionSummaryDetails")
	public String saveExecutionSummaryDetails(@RequestBody List<ExecutionSummaryDetailsDTO>executionSummaryDetailsDTO) {
		executionSummaryServiceImpl.saveExecutionSummaryDetails(executionSummaryDetailsDTO);
		return "success";
	} 
	

}
