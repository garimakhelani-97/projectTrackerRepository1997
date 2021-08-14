package com.smrc.gateone.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctc.wstx.util.StringUtil;
import com.smrc.gateone.dto.ProjectListDTO;
import com.smrc.gateone.model.Gate1ProjectProcessAssociation;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.model.Process;
import com.smrc.gateone.repository.Gate1ProjectProcessAssociationRepository;
import com.smrc.gateone.repository.PreviewsScreenRepository;
import com.smrc.gateone.repository.ProcessRepository;
import com.smrc.gateone.repository.ProjectRepository;
import com.smrc.gateone.service.ProjectListService;

@Service
public class ProjectListServiceImpl implements ProjectListService{

	@Autowired 
	private ProjectRepository projectRepository;
	
	@Autowired
	private PreviewsScreenRepository previewsScreenRepository;
	
	@Autowired
	ProcessRepository processRepository;
	
	@Autowired
	Gate1ProjectProcessAssociationRepository gate1ProjectProcessAssociationRepository;
	
	private static String EMPTY = StringUtils.EMPTY;
	private static String START = "START";
	private static String END = "END";
	
	
	public List<ProjectListDTO> getProjectList(){
		
		//get selected data from Project table;
		List<ProjectListDTO> customProjectList = projectRepository.findAllProjectListDTO();
		customProjectList = customProjectList.stream().map(customProject -> dateFormatterAction(customProject))
				.collect(Collectors.toList());
		List<Process> processMasterList = processRepository.findAll();
		//get project submission status.
		List<Integer> projectIds = customProjectList.stream().map(ProjectListDTO :: getId).collect(Collectors.toList());
		List<PreviewsScreen> projectSubmissionStatus = previewsScreenRepository.findAllByprojectId(projectIds);
		List<Gate1ProjectProcessAssociation> assignedProcessList = gate1ProjectProcessAssociationRepository.findAllByprojectId(projectIds);
		Map<Integer, String> processDataMap = prepareProcessMap(processMasterList);
		HashMap<String, List<String>> projectProcessListHashObj = hashMapConverterStartDateEndDate(assignedProcessList, processDataMap);
		//set submission status according to project.
		HashMap<Integer,PreviewsScreen> filteredProjectSubmissionStatus = prepareProjectSubmissionStatusMap(projectSubmissionStatus);
		PreviewsScreen isFreezedList = null;
		List<String> processNameList = null;
		List<String> startDateList = null;
		List<String> endDateList = null;
		for(ProjectListDTO singleProject:customProjectList) {
			isFreezedList = filteredProjectSubmissionStatus.get(singleProject.getId());
			
			processNameList = projectProcessListHashObj.get(EMPTY+singleProject.getId());
			startDateList = projectProcessListHashObj.get(START+singleProject.getId());
			endDateList = projectProcessListHashObj.get(END+singleProject.getId());
			
			if (processNameList != null && !processNameList.isEmpty()) {
				singleProject.setProcessNameList(processNameList);
				singleProject.setStartDate(startDateList);
				singleProject.setEndDate(endDateList);
			}
			if(isFreezedList != null) {
				singleProject.setIsFreezed(isFreezedList.getIsFreezed());
			}else{
				singleProject.setIsFreezed(0);
			}
		}
			return  customProjectList; 
	}
	
	private Map<Integer, String> prepareProcessMap(List<Process> processMasterList) {
		Map<Integer, String> processDataMap = new HashMap<Integer, String>();
		for (Process process : processMasterList) {
			if (process != null) {
				processDataMap.put(process.getId(), process.getName());
			}
		}
		return processDataMap;
	}
	
	
	// hashMapConverter for customProjectProcessAssociationList
	public HashMap<String, List<String>> hashMapConverterStartDateEndDate(
			List<Gate1ProjectProcessAssociation> assignedProcessList, Map<Integer, String> processDataMap) {
		HashMap<String, List<String>> projectProcessListHashObj = new HashMap<>();
		List<String> procsessNameList = null;
		List<String> startDateList = null;
		List<String> endDateList = null;
		for (Gate1ProjectProcessAssociation gate1ProjectProcessAssociation : assignedProcessList) {

			String processNameInProjectProcessAssn = processDataMap.get(gate1ProjectProcessAssociation.getProcessId());
			String startDate = getDateFormatInMonthYear(gate1ProjectProcessAssociation.getStartDate());
			String endDate = getDateFormatInMonthYear(gate1ProjectProcessAssociation.getEndDate());
			if (!StringUtils.isEmpty(processNameInProjectProcessAssn)) {

				procsessNameList = projectProcessListHashObj.get(EMPTY + gate1ProjectProcessAssociation.getProjectId());
				startDateList = projectProcessListHashObj.get(START + gate1ProjectProcessAssociation.getProjectId());
				endDateList = projectProcessListHashObj.get(END + gate1ProjectProcessAssociation.getProjectId());
				if (procsessNameList != null && !procsessNameList.isEmpty()) {
					procsessNameList.add(processNameInProjectProcessAssn);
					startDateList.add(startDate);
					endDateList.add(endDate);
					projectProcessListHashObj.put(EMPTY + gate1ProjectProcessAssociation.getProjectId(), procsessNameList);
					projectProcessListHashObj.put(START + gate1ProjectProcessAssociation.getProjectId(), startDateList);
					projectProcessListHashObj.put(END + gate1ProjectProcessAssociation.getProjectId(), endDateList);

				} else {
					ArrayList<String> procsessNameListObj = new ArrayList<>();
					ArrayList<String> startDateListObj = new ArrayList<>();
					ArrayList<String> endDateListObj = new ArrayList<>();
					procsessNameListObj.add(processNameInProjectProcessAssn);
					startDateListObj.add(startDate);
					endDateListObj.add(endDate);
					projectProcessListHashObj.put(EMPTY + gate1ProjectProcessAssociation.getProjectId(), procsessNameListObj);
					projectProcessListHashObj.put(START + gate1ProjectProcessAssociation.getProjectId(), startDateListObj);
					projectProcessListHashObj.put(END + gate1ProjectProcessAssociation.getProjectId(), endDateListObj);
				}
			}
		}
		return projectProcessListHashObj;
	}
	
	private String getDateFormatInMonthYear(String date) {
		String monthYearDate = "";
		if (StringUtils.isNotBlank(date)) {
			String[] dates = date.split(" ");
			monthYearDate = dates[1] + " " + dates[2];
		}
		return monthYearDate;
	}
		
	//hashMap for project submission status 
	private HashMap<Integer,PreviewsScreen> prepareProjectSubmissionStatusMap(List<PreviewsScreen> dataList){
	  HashMap<Integer,PreviewsScreen> submissionStatusMap = new HashMap<Integer,PreviewsScreen>();
	  for(PreviewsScreen singleProjectStatus: dataList) {
		  if(singleProjectStatus != null) {
			  submissionStatusMap.put(singleProjectStatus.getProjectId(), singleProjectStatus);
		  }
	  }
		return submissionStatusMap;
	}

	@Override
	public List<Gate1ProjectProcessAssociation> getProjectProcessAssociation(Integer projectId) {
		List<Gate1ProjectProcessAssociation> gate1ProjectProcessAssociations = gate1ProjectProcessAssociationRepository.findAllByProjectIdOrderByIdDesc(projectId);
		return gate1ProjectProcessAssociations;
	}
	
	private ProjectListDTO dateFormatterAction(ProjectListDTO projectListDTO) {
		String updatedDate = projectListDTO.getUpdatedDate();
		if(updatedDate == null || StringUtils.isEmpty(updatedDate)) {
			return projectListDTO;
		}
		SimpleDateFormat stringToDateConverter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dateToStringConverter = new SimpleDateFormat("dd-MMM-YYYY");
		Date dateObj = null;
		String finalDate = null;
		try {
			dateObj = stringToDateConverter.parse(updatedDate);
			finalDate = dateToStringConverter.format(dateObj);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		projectListDTO.setUpdatedDate(finalDate);

		return projectListDTO;
	}
}
