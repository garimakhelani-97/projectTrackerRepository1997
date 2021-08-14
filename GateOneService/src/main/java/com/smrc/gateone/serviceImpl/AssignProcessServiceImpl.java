package com.smrc.gateone.serviceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gateone.model.AccountGateDetails;
import com.smrc.gateone.model.Gate1ProjectProcessAssociation;
import com.smrc.gateone.model.PipAction;
import com.smrc.gateone.model.PipFinancials;
import com.smrc.gateone.model.Process;
import com.smrc.gateone.model.Project;
import com.smrc.gateone.model.ProjectGateDetails;
import com.smrc.gateone.model.ProjectMetricsDetail;
import com.smrc.gateone.model.ProjectProcessAssociationModel;
import com.smrc.gateone.model.UserRoleAssociation;
import com.smrc.gateone.repository.AccountGateDetailsRepository;
import com.smrc.gateone.repository.BusinessUnitWiseUserRepository;
import com.smrc.gateone.repository.Gate1ProjectProcessAssociationRepository;
import com.smrc.gateone.repository.PipActionRepository;
import com.smrc.gateone.repository.PipFinancialsRepository;
import com.smrc.gateone.repository.ProcessRepository;
import com.smrc.gateone.repository.ProjectGateDetailRepository;
import com.smrc.gateone.repository.ProjectMetricsDetailRepository;
import com.smrc.gateone.repository.ProjectRepository;
import com.smrc.gateone.repository.UserRoleAssociationRepository;
import com.smrc.gateone.service.AssignProcessService;

@Service
public class AssignProcessServiceImpl implements AssignProcessService {
	@Autowired
	ProcessRepository processRepository;
	
	@Autowired
	UserRoleAssociationRepository userRoleAssociationRepository;
	
	@Autowired
	private Gate1ProjectProcessAssociationRepository gate1_project_process_associationRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PipActionRepository pipActionRepository;
	
	@Autowired 
	private PipFinancialsRepository pipFinancialsRepository;
	
	@Autowired
	private AccountGateDetailsRepository accountGateDetailsRepository;
	
	@Autowired
	private ProjectGateDetailRepository projectGateDetailRepository;
	
	@Autowired
	private ProjectMetricsDetailRepository projectMetricsDetailRepository;
	
	@Autowired
	private SyncGPORServiceImpl syncGPORServiceImpl;
	
	@Autowired
	private BusinessUnitWiseUserRepository businessUnitWiseUserRepository;
	
	@Override
	public ProjectProcessAssociationModel getProcessAssociationData(String projectId) {
		ProjectProcessAssociationModel projectProcessAssociationModel = new ProjectProcessAssociationModel();
		// get project CG id
		Integer customerGroupId = projectRepository.findCustomerGroupIdById(Integer.parseInt(projectId));
		/* get user id by Customer Group */
		List<Integer> userIdList = businessUnitWiseUserRepository.getUserIdByBuId(customerGroupId);

		List<UserRoleAssociation> userDetails = userRoleAssociationRepository.findAllByUserIdIn(userIdList);

		// get all process data
		List<Process> processList = processRepository.findAll();
		// processList
		projectProcessAssociationModel.setProcessList(processList);
		List<UserRoleAssociation> programControllerList = userDetails.stream().filter(data -> data.getRoleId() == 3)
				.sorted(Comparator.comparing(UserRoleAssociation::getUserName, String::compareToIgnoreCase))
				.collect(Collectors.toList());
		projectProcessAssociationModel.setProjectControllerList(programControllerList);

		List<UserRoleAssociation> projectManagerList = userDetails.stream().filter(data -> data.getRoleId() == 2)
				.sorted(Comparator.comparing(UserRoleAssociation::getUserName, String::compareToIgnoreCase))
				.collect(Collectors.toList());
		projectProcessAssociationModel.setProjectManagerList(projectManagerList);

		List<UserRoleAssociation> programManagerList = userDetails.stream().filter(data -> data.getRoleId() == 6)
				.sorted(Comparator.comparing(UserRoleAssociation::getUserName, String::compareToIgnoreCase))
				.collect(Collectors.toList());
		projectProcessAssociationModel.setProgramManagerList(programManagerList);

		return projectProcessAssociationModel;
	}
	
	public String saveProcessAssociationData (String projectId,Map processAssignMapper) {
		
		System.out.println(processAssignMapper);
		System.out.println(projectId);
		List<Map> processes=new ArrayList<Map>();
		processes=(List<Map>) (processAssignMapper.get("processWithDateList")==null ?new ArrayList<Map>():processAssignMapper.get("processWithDateList"));
		if(processes.size()==0) {
			Integer ProjectControllerId=Integer.parseInt(((Map)processAssignMapper.get("projectController")).get("id").toString());
			Integer ProjectManagerId=Integer.parseInt(((Map)processAssignMapper.get("projectManager")).get("id").toString());
			Integer programManagerId=Integer.parseInt(((Map)processAssignMapper.get("programManager")).get("id").toString());
			Gate1ProjectProcessAssociation existenceEntity= gate1_project_process_associationRepository.findByprojectIdprocessIdNull(Integer.parseInt(projectId));
			Gate1ProjectProcessAssociation entity=new Gate1ProjectProcessAssociation();
			if(existenceEntity!=null) {
				entity=existenceEntity;
			}
			entity.setProjectId(Integer.parseInt(projectId));
//			entity.setProcessId(processid);
//			entity.setStartDate(getMonthYear(startDate.split("T")[0]));
//			String startDate1=getMonthYear(startDate.split("T")[0]);
//			entity.setEndDate(getMonthYear(endDate.split("T")[0]));
			entity.setRecordStatusId(1);
			entity.setProjectControllerId(ProjectControllerId);
			entity.setProjectManagerId(ProjectManagerId);
			entity.setProgramManagerId(programManagerId);
			entity.setCreatedDate(new Date().toString());
			entity.setUpdatedDate(new Date().toString());
			
			Gate1ProjectProcessAssociation project_process_associationMapper=gate1_project_process_associationRepository.save(entity);
			
		}
		for(int i=0;i<processes.size();i++) {
			Map processMap=((Map)((Map)processes.get(i)).get("process"));
			String startDate=((Map)processes.get(i)).get("startDate").toString();
			String endDate=((Map)processes.get(i)).get("endDate").toString();
			Integer processid=Integer.parseInt(processMap.get("id").toString());
			Integer programManagerId=Integer.parseInt(((Map)processAssignMapper.get("programManager")).get("id").toString());
			Integer ProjectControllerId=Integer.parseInt(((Map)processAssignMapper.get("projectController")).get("id").toString());
			Integer ProjectManagerId=Integer.parseInt(((Map)processAssignMapper.get("projectManager")).get("id").toString());
			Gate1ProjectProcessAssociation entity=new Gate1ProjectProcessAssociation();
//		  check  data existence
			Gate1ProjectProcessAssociation existenceEntity= gate1_project_process_associationRepository.findByprojectIdprocessId(Integer.parseInt(projectId),processid);
			if(existenceEntity!=null) {
				entity=existenceEntity;
			}
			entity.setProjectId(Integer.parseInt(projectId));
			entity.setProcessId(processid);
			entity.setStartDate(getMonthYear(startDate.split("T")[0]));
//			String startDate1=getMonthYear(startDate.split("T")[0]);
			entity.setEndDate(getMonthYear(endDate.split("T")[0]));
			entity.setRecordStatusId(1);
			entity.setProjectControllerId(ProjectControllerId);
			entity.setProjectManagerId(ProjectManagerId);
			entity.setProgramManagerId(programManagerId);
			entity.setCreatedDate(new Date().toString());
			entity.setUpdatedDate(new Date().toString());
			
			Gate1ProjectProcessAssociation project_process_associationMapper=gate1_project_process_associationRepository.save(entity);
			if(processid==1) {//sync data in gpor 
				Optional<Project> projectMapper=projectRepository.findById(Integer.parseInt(projectId));
				List<PipAction> pipActionMapper=(List<PipAction>) pipActionRepository.findPipActionDataByProject(Integer.parseInt(projectId));
				List<PipFinancials>  pipFinancialsMapper=pipFinancialsRepository.findPipFinancialDataByProject(Integer.parseInt(projectId));
				List<AccountGateDetails> accountGateDetailsMapper=accountGateDetailsRepository.findAllByprojectId(Integer.parseInt(projectId));
				List<ProjectGateDetails> projectGateDetailsMapper=projectGateDetailRepository.findByprojectId(Integer.parseInt(projectId));
				List<ProjectMetricsDetail> projectMetricsDetail=projectMetricsDetailRepository.findByprojectId(Integer.parseInt(projectId));
				System.out.println(pipActionMapper);
				List<HashMap> newProjectGtaeDetailsList=new ArrayList<HashMap>();
				for(int j=0;j<projectGateDetailsMapper.size();j++) {
					ProjectGateDetails columnData=	(ProjectGateDetails) projectGateDetailsMapper.get(j);
				Map newColumnMap=new HashMap();
				newColumnMap.put("projectId", columnData.getProjectId());
				newColumnMap.put("gateId", columnData.getGateId());
				newColumnMap.put("isFreezed", columnData.getIsFreezed());
				newColumnMap.put("initialDate",columnData.getInitialDate().toString());
				
					newProjectGtaeDetailsList.add((HashMap) newColumnMap);
				}
				
				Map requestBodyMap=new HashMap();
				requestBodyMap.put("projectMapper",projectMapper);
				requestBodyMap.put("pipActionMapper",pipActionMapper);
				requestBodyMap.put("pipFinancialsMapper",pipFinancialsMapper);
				requestBodyMap.put("accountGateDetailsMapper",accountGateDetailsMapper);
				requestBodyMap.put("projectGateDetailsMapper",newProjectGtaeDetailsList);
				requestBodyMap.put("projectMetricsDetail",projectMetricsDetail);
				requestBodyMap.put("project_process_associationMapper", project_process_associationMapper);

				syncGPORServiceImpl.addProjectProcessAndTransactionsData(requestBodyMap);
				
			}
		}
		return "success";
	}
	public String getMonthYear(String date) {
		Map dateMap=new HashMap();
		dateMap.put("01", "January");
		dateMap.put("02", "February");
		dateMap.put("03", "March");
		dateMap.put("04", "April");
		dateMap.put("05", "May");
		dateMap.put("06", "June");
		dateMap.put("07", "July");
		dateMap.put("08", "August");
		dateMap.put("09", "September");
		dateMap.put("10", "October");
		dateMap.put("11", "November");
		dateMap.put("12", "December");
		String[] dateArray=date.split("-");
		String Month=dateMap.get(dateArray[1]).toString();
		System.out.println(date);
		return "01"+" "+Month+" "+dateArray[0];
	}

}
