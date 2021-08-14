package com.smrc.gpor.service;

import java.util.List;

import com.smrc.gpor.dto.ExecutionSummaryDetailsDTO;

public interface ExecutionSummaryService {

	public List<ExecutionSummaryDetailsDTO> getExecutionDetails(String month,String year);
	
	public String saveExecutionSummaryDetails(List<ExecutionSummaryDetailsDTO> executionSummaryDetailsDTO);
}
