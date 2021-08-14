package com.smrc.gpor.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.dto.ExecutionSummaryDetailsDTO;
import com.smrc.gpor.model.ExecutionSummary;
import com.smrc.gpor.model.ExecutionSummaryDetails;
import com.smrc.gpor.model.ProjectMetricsDetail;
import com.smrc.gpor.repository.ExecutionSummaryDetailsRepository;
import com.smrc.gpor.repository.ExecutionSummaryRepository;
import com.smrc.gpor.service.ExecutionSummaryService;
@Service
public class ExecutionSummaryServiceImpl implements ExecutionSummaryService {

	@Autowired
	ExecutionSummaryRepository executionSummaryRepository;
	
	@Autowired
	ExecutionSummaryDetailsRepository executionSummaryDetailsRepository;
	
	
	@Override
	public List<ExecutionSummaryDetailsDTO> getExecutionDetails(String month, String year) {
		List<ExecutionSummary> executionSummaryList = executionSummaryRepository.findAll();
		List<ExecutionSummaryDetails> executionDetailsExistingData = executionSummaryDetailsRepository.findAllByMonthAndYear(month,year);
		List<ExecutionSummaryDetailsDTO> executionSummaryDetailsDTOList = new ArrayList<ExecutionSummaryDetailsDTO>();
		if(executionDetailsExistingData.size() == 0) {
			for(ExecutionSummary executionSummary:executionSummaryList) {
				ExecutionSummaryDetailsDTO executionSummaryDetailsDTO = new ExecutionSummaryDetailsDTO();
				executionSummaryDetailsDTO.setName(executionSummary.getName());
				executionSummaryDetailsDTO.setMonth(month);
				executionSummaryDetailsDTO.setYear(year);
				executionSummaryDetailsDTO.setKpiId(executionSummary.getId());
				executionSummaryDetailsDTO.setSummary(null);
				executionSummaryDetailsDTO.setUserId(null);
				executionSummaryDetailsDTOList.add(executionSummaryDetailsDTO);
			}
		} else {
			HashMap<Integer, ExecutionSummaryDetails> custom = prepareExecutionSummaryDetailMap(executionDetailsExistingData);
			ExecutionSummaryDetails executionSummaryDetails = null;
			for(ExecutionSummary executionSummary:executionSummaryList) {
				ExecutionSummaryDetailsDTO executionSummaryDetailsDTO = new ExecutionSummaryDetailsDTO();
				executionSummaryDetailsDTO.setName(executionSummary.getName());
				executionSummaryDetails = custom.get(executionSummary.getId());
				if (executionSummaryDetails != null) {
					executionSummaryDetailsDTO.setKpiId(executionSummaryDetails.getKpiId());
					executionSummaryDetailsDTO.setMonth(executionSummaryDetails.getMonth());
					executionSummaryDetailsDTO.setYear(executionSummaryDetails.getYear());
					executionSummaryDetailsDTO.setSummary(executionSummaryDetails.getSummary());
					executionSummaryDetailsDTO.setUserId(executionSummaryDetails.getUserId());
				}else {
					executionSummaryDetailsDTO.setKpiId(executionSummary.getId());
					executionSummaryDetailsDTO.setMonth(month);
					executionSummaryDetailsDTO.setYear(year);
					executionSummaryDetailsDTO.setSummary(null);
				}
				executionSummaryDetailsDTOList.add(executionSummaryDetailsDTO);
			}
		}
		return executionSummaryDetailsDTOList;
	}


	private HashMap<Integer, ExecutionSummaryDetails> prepareExecutionSummaryDetailMap(
			List<ExecutionSummaryDetails> executionDetailsExistingData) {
		HashMap<Integer, ExecutionSummaryDetails> executionSummaryDataMap = new HashMap<Integer, ExecutionSummaryDetails>();
		for(ExecutionSummaryDetails executionSummaryDetails: executionDetailsExistingData) {
			if(executionDetailsExistingData != null) {
				executionSummaryDataMap.put(executionSummaryDetails.getKpiId(), executionSummaryDetails);
			}
		}
		return executionSummaryDataMap;
	}


	@Override
	public String saveExecutionSummaryDetails(List<ExecutionSummaryDetailsDTO> executionSummaryDetailsDTOList) {
		 ExecutionSummaryDetails executionSummaryDetails = null;
		 for(ExecutionSummaryDetailsDTO executionSummaryDetailsDTO: executionSummaryDetailsDTOList ) {
			 //Check existing data
			 executionSummaryDetails = new ExecutionSummaryDetails();
			 List<ExecutionSummaryDetails> data = executionSummaryDetailsRepository.findAllByKpiIdAndMonthAndYear
						(executionSummaryDetailsDTO.getKpiId(),executionSummaryDetailsDTO.getMonth(),executionSummaryDetailsDTO.getYear());
						if (data.size() > 0) {
							executionSummaryDetails = data.get(0);
						}
						executionSummaryDetails.setKpiId(executionSummaryDetailsDTO.getKpiId());
						executionSummaryDetails.setSummary(executionSummaryDetailsDTO.getSummary());
						executionSummaryDetails.setMonth(executionSummaryDetailsDTO.getMonth());
						executionSummaryDetails.setYear(executionSummaryDetailsDTO.getYear());
						executionSummaryDetails.setProcessId(1);

						executionSummaryDetailsRepository.save(executionSummaryDetails);

					}
					return "successs";
				}
		
	}
