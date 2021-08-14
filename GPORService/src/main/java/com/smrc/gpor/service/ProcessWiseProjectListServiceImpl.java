package com.smrc.gpor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.smrc.gpor.dto.WorkflowApproverAndTransectionDTO;
import com.smrc.gpor.dto.WorkflowDetailsDTO;
import com.smrc.gpor.dto.WorkflowTransectionDetailDTO;
import com.smrc.gpor.model.CustomerGroupUserMapping;
import com.smrc.gpor.model.Gate;
import com.smrc.gpor.model.ProcessWiseProject;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.ProjectActionDetail;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.model.ProjectGateDetail;
import com.smrc.gpor.model.ProjectMetricsDetail;
import com.smrc.gpor.model.ProjectProcessAssociation;
import com.smrc.gpor.model.ProjectSubmissionStatus;
//import com.smrc.gpor.model.ProjectProcessAssociation;
import com.smrc.gpor.model.Status;
import com.smrc.gpor.model.UserRoleAssociation;
import com.smrc.gpor.model.WorkFlowSubmission;
import com.smrc.gpor.repository.CustomerGroupUserMappingRepository;
import com.smrc.gpor.repository.GateRepository;
import com.smrc.gpor.repository.MetricsRepository;
import com.smrc.gpor.repository.ProjectActionDetailRepository;
import com.smrc.gpor.repository.ProjectApproverRepository;
import com.smrc.gpor.repository.ProjectGateDetailRepository;
import com.smrc.gpor.repository.ProjectMetricsDetailRepository;
import com.smrc.gpor.repository.ProjectProcessAssociationRepository;
import com.smrc.gpor.repository.ProjectRepository;
import com.smrc.gpor.repository.ProjectSubmissionStatusRepository;
import com.smrc.gpor.repository.StatusRepository;
import com.smrc.gpor.repository.UserRoleAssociationRepository;
import com.smrc.gpor.repository.WorkFlowSubmissionRepository;

@Service
public class ProcessWiseProjectListServiceImpl implements ProcessWiseProjectListService {

	private static String SEPERATOR = "-";
	@Autowired
	ProjectProcessAssociationRepository projectProcessAssociationRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectGateDetailRepository projectGateDetailRepository;

	@Autowired
	GateRepository gateRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	MetricsRepository metricsRepository;

	@Autowired
	ProjectMetricsDetailRepository projectMetricsDetailRepository;

	@Autowired
	WorkFlowSubmissionRepository workFlowSubmissionRepository;
	
	@Autowired 
	ProjectApproverRepository projectApproverRepository;
	
	@Autowired
	UserRoleAssociationRepository userRoleAssociationRepository;
	
	@Autowired
	ProjectActionDetailRepository projectActionDetailRepository;
	
	@Autowired
	ProjectSubmissionStatusRepository projectSubmissionStatusRepository;

	@Value("${processWiseProjectListPageSize}")
	private String pageSize;
	
	private static final String APPROVED = "Approved";
	private static final String RETURNED = "Returned";
	private static final String SUBMITTED = "Submitted";
	private static final String GREEN = "Green";
	private static final String YELLOW = "Yellow";
	private static final String GREY = "Grey";
	@Autowired
	private CustomerGroupUserMappingRepository customerGroupUserMappingRepository;

	@Override
	public Page<ProcessWiseProject> geProcessWiseProjectList(Integer processId, String reportedMonth,Integer userId) {

		List<ProjectProcessAssociation> projectProcessAssociationList = projectProcessAssociationRepository.findAllbyIDDesc();

		projectProcessAssociationList = projectProcessAssociationList.stream().filter(p -> (p.getStartDate() != null)
				&& (p.getEndDate() != null)
				&& ((dateFormat(reportedMonth).after(dateFormat(p.getStartDate())))
						&& (dateFormat(reportedMonth).before(dateFormat(p.getEndDate()))))
				|| (p.getStartDate() != null && (dateFormat(p.getStartDate()).equals(dateFormat(reportedMonth))))
				|| (p.getEndDate() != null && (dateFormat(p.getEndDate()).equals(dateFormat(reportedMonth)))))
				.collect(Collectors.toList());
		/**role is project controller and project manager**/
		//find roles of existing userid
		Integer userPk=userRoleAssociationRepository.findIdByUserId(userId);
		List <UserRoleAssociation> roleList= projectRepository.getRoles(userId);
		List<ProjectProcessAssociation>	projectProcessAssociationListPC=new ArrayList<ProjectProcessAssociation>();
		List<ProjectProcessAssociation> projectProcessAssociationListPM=new ArrayList<ProjectProcessAssociation>();
		List<ProjectProcessAssociation> projectProcessAssociationListProgramManager=new ArrayList<ProjectProcessAssociation>();
		Boolean pcpmFlag=false;
		Boolean programManagerFlag=false;
		for (UserRoleAssociation userRoleAssociation : roleList) {
			if(userRoleAssociation.getRoleName().equals("Project Controller")) {
				pcpmFlag=true;
				System.out.println(userRoleAssociation.getRoleName());
				
				projectProcessAssociationListPC=  projectProcessAssociationList.stream().
						filter(p -> (p.getProjectControllerId().equals(userPk))).collect(Collectors.toList());
				
			}
			else if(userRoleAssociation.getRoleName().equals("Project Manager")) {
				pcpmFlag=true;
				projectProcessAssociationListPM= (List<ProjectProcessAssociation>) projectProcessAssociationList.stream().
						filter(p -> (p.getProjectManagerId().equals(userPk))).collect(Collectors.toList());	
			}
			/**role is project controller and program manager**/
			else if(userRoleAssociation.getRoleName().equals("Program Manager")) {
				programManagerFlag=true;
				projectProcessAssociationListProgramManager=projectProcessAssociationList.stream().
				filter(p -> (p.getProgramManagerId()!=null&&p.getProgramManagerId().equals(userPk))).collect(Collectors.toList());
			}
			
		}
		
		
		if(pcpmFlag) {
			pcpmFlag=true;
			projectProcessAssociationList=new ArrayList<ProjectProcessAssociation>();
			projectProcessAssociationList.addAll( projectProcessAssociationListPC);
			projectProcessAssociationList.addAll( projectProcessAssociationListPM);
			projectProcessAssociationList=projectProcessAssociationList.stream().distinct()
                    									.collect(Collectors.toList());
		}
		else if(programManagerFlag) {
			projectProcessAssociationList=new ArrayList<ProjectProcessAssociation>();
			projectProcessAssociationList.addAll(projectProcessAssociationListProgramManager);
		}
		System.out.println(projectProcessAssociationList);
		
		/**role is project controller and project manager**/
		List<Integer> projectIdList = projectProcessAssociationList.stream()
				.map(projectProcessAssociation -> projectProcessAssociation.getProjectId())
				.collect(Collectors.toList());

		List<Project> projectlist = projectRepository.findAllById(projectIdList);		
		List<ProcessWiseProject> processWiseProjectList = new ArrayList<>();
		
		Date dummyStatusDate = getDummyStatusDate();

		for (Project project : projectlist) {

			ProcessWiseProject processWiseProject = new ProcessWiseProject();

			processWiseProject.setId(project.getId());
			processWiseProject.setProjectId(project.getProjectRefId());
			processWiseProject.setProjectName(project.getName());
			processWiseProject.setSopDate(project.getSopMonth() + " " + project.getSopYear());

			// Calculate Year
			Integer ispEopYear = project.getIspEopYear();
			Integer ispSopyear = project.getIspSopYear();
			if (ispEopYear != null && ispSopyear != null) {
				Integer projectLife = ispEopYear - ispSopyear;
				processWiseProject.setLife(projectLife);
			}
				processWiseProject.setCustomerName(project.getCustomerName());
				processWiseProject.setCustomerGroup(project.getCustomerGroupName());
		    	processWiseProject.setPlantName(project.getUnitName());
		    	processWiseProject.setCurrencyId(project.getCurrenyId());
		    	processWiseProject.setCurrencyName(project.getCurrencyName());
		    	processWiseProject.setUnitDescription(project.getUnitDescription());
		    	

			// Gate
			Gate gateObj = getGate(reportedMonth, project);
			if (gateObj != null) {
				processWiseProject.setGate(gateObj.getDescription());

			}

			// OverALLStatus
			Integer metricsId = 1;
			Status status = getOverAllStatus(project.getId(), metricsId, reportedMonth);
			if (status != null) {
				processWiseProject.setOverallStatus(status.getDescription());
			}

			// workflow
            //WorkFlowSubmission workFlowSubmission = getWorkFlow(project.getId(), reportedMonth);
			String [] monthArray=reportedMonth.split(" ");
            List<ProjectApprover> workFlowSubmission=projectApproverRepository.getApprovedstatus(project.getId(),monthArray[0].substring(0,3),monthArray[1], 18);
           if(workFlowSubmission.size()>0 ) {
        	   
           processWiseProject.setWorkflow(statusRepository.getStatus(workFlowSubmission.get(0).getApproveStatus()));
           if(workFlowSubmission.get(0).getCreatedDate()!=null && (!workFlowSubmission.get(0).getCreatedDate().isEmpty())) {
           try {
               Date statusDate= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(workFlowSubmission.get(0).getCreatedDate());
               processWiseProject.setStatusDate(statusDate);
           } catch (ParseException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
                  
          
           }}
           else {
               List<ProjectSubmissionStatus> p=projectSubmissionStatusRepository.findsubmissionStatus(project.getId(),monthArray[0].substring(0,3),monthArray[1]);
              
               if(p.size()>0 && p.get(0).getSubmissionFlag()!=null && p.get(0).getSubmissionFlag()==15 ) {
               processWiseProject.setWorkflow(statusRepository.getStatus(p.get(0).getSubmissionFlag()));               
              
               }
               else if(p.size()>0 && p.get(0).getSubmissionFlag()!=null && p.get(0).getSubmissionFlag()==16) {
                   processWiseProject.setWorkflow(statusRepository.getStatus(p.get(0).getSubmissionFlag()));               
                  
                   }
               else {
                   processWiseProject.setWorkflow("Not Submitted");   
//                   processWiseProject.setStatusDate(dummyStatusDate);
                   
               }
              
               if(p.size()>0 && p.get(0).getSubmittedDate()!=null && (!p.get(0).getSubmittedDate().isEmpty())) {
               try {
                   Date statusDate= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(p.get(0).getSubmittedDate());
                   processWiseProject.setStatusDate(statusDate);
               } catch (ParseException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
                      
              
               }
           }
			// processWiseProject
			processWiseProjectList.add(processWiseProject);

		}
		/**getProjectsRoleWiseBU **/		  
//		List<ProcessWiseProject> filterProjectsRoleWise= getProjectsRoleWise(processWiseProjectList,userId);
		List<ProcessWiseProject> projectProcessAssociationListBU=new ArrayList<ProcessWiseProject>();
		for (UserRoleAssociation userRoleAssociation : roleList) {
			
		 if(userRoleAssociation.getRoleName().equals("Business Unit Head")) {
			projectProcessAssociationListBU=getProjectsRoleWiseBU(processWiseProjectList,userId);
			processWiseProjectList=projectProcessAssociationListBU;
			}
//		 System.out.println(projectProcessAssociationListBU);
		}
		 /**getProjectsRoleWise **/  
		 
		// Converting List<Project> to Page<Project>

		int total = processWiseProjectList.size();
//		processWiseProjectList.stream().
		processWiseProjectList.sort((ProcessWiseProject s1, ProcessWiseProject s2)->s2.getId()-s1.getId()); 
		Integer size = Integer.parseInt(pageSize);
		Pageable pageable = PageRequest.of(0, size);// , Sort.by(Order.desc("id")
		Page<ProcessWiseProject> processWiseProjectListPage = new PageImpl<>(processWiseProjectList, pageable, total);
		return processWiseProjectListPage;

	}

	private Date getDummyStatusDate() {
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 22);
		calendar.set(Calendar.YEAR, 2021);
        return calendar.getTime();
	}

	// Gate
	@Override
	public Gate getGate(String reportedMonth, Project project) {

		List<ProjectGateDetail> projectGateDetailList = projectGateDetailRepository.findByProjectIdAndReportedMonthAndIsFreezed(
				project.getId(), reportedMonth,1, Sort.by("projectId").ascending().and(Sort.by("statusId").ascending()));
		if (!projectGateDetailList.isEmpty()) {
			ProjectGateDetail projectGateDetailObj = projectGateDetailList.get(0);

			if (!projectGateDetailList.isEmpty()) {
				List<ProjectGateDetail> projectGateDetailListBasedOnStatusId = projectGateDetailList.stream().filter(
						projectGateDetail -> projectGateDetail.getStatusId() == projectGateDetailObj.getStatusId())
						.collect(Collectors.toList());

				if (!projectGateDetailListBasedOnStatusId.isEmpty()) {
//					Comparator<ProjectGateDetail> projectGateDetailComparator = Comparator
//							.comparing(ProjectGateDetail::getGateId);
//					List<ProjectGateDetail> projectGateDetails2 = projectGateDetailListBasedOnStatusId.stream()
//							.sorted(projectGateDetailComparator).collect(Collectors.toList());
					
					
					List<Integer> ids = projectGateDetailListBasedOnStatusId.stream().map(x -> x.getGateId())
							.collect(Collectors.toList());
					List<Gate> gateList = gateRepository.findAllById(ids);
					Comparator<Gate> gateComparator = Comparator.comparing(Gate::getOrder);
					if (!gateList.isEmpty()) {
						List<Gate> sortedGateList = gateList.stream().sorted(gateComparator)
								.collect(Collectors.toList());
						if (!sortedGateList.isEmpty()) {
							return sortedGateList.get(sortedGateList.size() - 1);
						}
					}
					
					

//					if (!projectGateDetails2.isEmpty()) {
//						ProjectGateDetail projectGateDetail = projectGateDetailList.get(projectGateDetails2.size() - 1);
//						Optional<Gate> gateObj = gateRepository.findById(projectGateDetail.getGateId());
//						if (gateObj.isPresent()) {
//							return gateObj.get();
//						}
//					}
				}
			}
		}

		return null;
	}

	// OverAllStatus
	@Override
	public Status getOverAllStatus(Integer projectId, Integer metricsId, String reportedMonth) {

		ProjectMetricsDetail projectMetricsDetail = projectMetricsDetailRepository
				.findFirstByProjectIdAndReportedMonthAndMetricsId(projectId, reportedMonth, metricsId,
						Sort.by("projectId").ascending().and(Sort.by("statusId")));

		if (projectMetricsDetail != null) {
			Optional<Status> statusOptional = statusRepository.findById(projectMetricsDetail.getStatusId()==null?0: projectMetricsDetail.getStatusId());
			if (statusOptional.isPresent()) {
				return statusOptional.get();
			}
		}
		return null;
	}

	// workFlowSubmission
	@Override
	public WorkFlowSubmission getWorkFlow(Integer projectId, String reportedMonth) {

		WorkFlowSubmission workFlowSubmission = workFlowSubmissionRepository.findFirstByProjectIdAndReportedMonth(
				projectId, reportedMonth, Sort.by("projectId").descending().and(Sort.by("submitOn").descending()));
		return workFlowSubmission;

	}
	
	private Date dateFormat(String dateVal) {
		Date date = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM yyyy");

		try {
		String[] dateArray=	dateVal.split(" ");
		if(dateArray.length==2) {
			date = dateFormatter.parse(dateVal);
		}
		else {
			date = dateFormatter.parse(dateArray[1].substring(0,3)+" "+dateArray[2]);
		}
		
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	@Override
	public WorkflowApproverAndTransectionDTO getWorkFlowTransections(String projectId, String reportedDate) {
		String month = reportedDate.substring(0, 3);
		String year = reportedDate.substring(reportedDate.indexOf(" ") + 1);
		List<WorkflowDetailsDTO> workflowDetailsDTOList =null;
		List<WorkflowTransectionDetailDTO> workflowTransectionDetailDTOListOne = null;
		WorkflowApproverAndTransectionDTO workflowApproverAndTransectionDTO = new WorkflowApproverAndTransectionDTO();
		/* Get Active approvers list */
		List<ProjectApprover> list = null;
		 list = projectApproverRepository
				.findAllByProjectIdAndMonthAndYearAndStatusId(Integer.parseInt(projectId), month, year,10);
		if (list.size() > 0) {
			ProjectApprover projectsubmitterData = prepareProjectControllerData(projectId, month, year);
			list.add(0, projectsubmitterData);
			workflowDetailsDTOList = prepareWorkflowDetails(list);
			workflowTransectionDetailDTOListOne = prepareWorkflowTransectionData(projectId, month, year);
			workflowApproverAndTransectionDTO.setWorkflowDetails(workflowDetailsDTOList);
			workflowApproverAndTransectionDTO.setTransectionDetails(workflowTransectionDetailDTOListOne);
		}else {
			workflowTransectionDetailDTOListOne = prepareWorkflowTransectionData(projectId, month, year);
			workflowApproverAndTransectionDTO.setWorkflowDetails(workflowDetailsDTOList);
			workflowApproverAndTransectionDTO.setTransectionDetails(workflowTransectionDetailDTOListOne);
		}
		return workflowApproverAndTransectionDTO;
	}
	

	

	private ProjectApprover prepareProjectControllerData(String projectId,String month,String year) {
		Integer formatttedProjectId = Integer.parseInt(projectId);
		Integer projetSubmitterId = projectSubmissionStatusRepository.getsubmittedByIdAndMonthAndYear(formatttedProjectId, month, year);
		UserRoleAssociation projectSubmitterDetails = userRoleAssociationRepository.findByUserId(projetSubmitterId);
		Integer projectSubmissionStatus = projectSubmissionStatusRepository.getSubmissionFlagByIdAndMonthAndYear(
				formatttedProjectId,month,year);
		ProjectApprover projectApprover = new ProjectApprover();
		if (projectSubmitterDetails != null) {
			projectApprover.setApprovedBy(projectSubmitterDetails.getUserId());
			projectApprover.setRoleId(projectSubmitterDetails.getRoleId());
			projectApprover.setRoleName(projectSubmitterDetails.getRoleName());
			/*
			 * project submission status == 15 mean project Submitted project submission
			 * status == 16 means project not submitted
			 */
			if (projectSubmissionStatus != null) {
				if (projectSubmissionStatus == 15) {
					projectApprover.setApproveStatus(15); // approver status 15 = submitted
				} else {
					projectApprover.setApproveStatus(14); // approver status 14 = Pending
				}
			}
		}
		projectApprover.setOrderId(0);
		projectApprover.setStatusId(10);
		return projectApprover;
	}

	private Map<Integer, ProjectActionDetail> prepareCommentMapper(List<ProjectActionDetail> projectActionDetails) {
		HashMap<Integer, ProjectActionDetail> projectActionDataMap = new HashMap<Integer, ProjectActionDetail>();
		for(ProjectActionDetail singleRecord:projectActionDetails) {
			if(singleRecord != null) {
				projectActionDataMap.put(singleRecord.getCreatedBy(), singleRecord);
			}
		}
		return projectActionDataMap;
	}
	private Map<Integer, Status> prepareStatustMapper(List<Status> statusList) {
		HashMap<Integer, Status> workflowStatusDataMap = new HashMap<Integer, Status>();
		for(Status singleStatus:statusList) {
			if(singleStatus != null) {
				workflowStatusDataMap.put(singleStatus.getId(), singleStatus);
			}
		}
		return workflowStatusDataMap;
	}
	
	private Map<String, UserRoleAssociation> prepareUserRoleAssociationData(List<UserRoleAssociation> userDetails) {
		
		Map<String, UserRoleAssociation>  map = new HashMap<String, UserRoleAssociation>();
		
		for (UserRoleAssociation userRoleAssociation : userDetails) {
			map.put(userRoleAssociation.getUserId()+SEPERATOR+userRoleAssociation.getRoleId(), userRoleAssociation);
		}
		return map;
	}

	
	private List<ProcessWiseProject> getProjectsRoleWiseBU(List<ProcessWiseProject> projects,Integer userId){
		List<ProcessWiseProject> finalList=new ArrayList<ProcessWiseProject>();
//		ProjectProcessAssociation getRole=projectProcessAssociationRepository.findByUserId();
		//find all customer group
		List<CustomerGroupUserMapping> customerGroupList=  customerGroupUserMappingRepository.findAllCustomerGroups(userId);
		for (CustomerGroupUserMapping customerGroup : customerGroupList) {
			List<ProcessWiseProject> tempProjects=projects.stream().filter
					(p->(p.getCustomerGroup().equals(customerGroup.getCustomerGroupName()))).collect(Collectors.toList());
			finalList.addAll(tempProjects);
			
		}
		return finalList;
	}
	
	private List<WorkflowTransectionDetailDTO> prepareWorkflowTransectionData(String projectId, String month,
			String year) {
//		Get project action details
		List<WorkflowTransectionDetailDTO> workflowTransectionDetailDTOList = new ArrayList<WorkflowTransectionDetailDTO>();
		List<ProjectActionDetail> projectActionDetails = projectActionDetailRepository
				.findAllByProjectIdAndMonthAndYear(Integer.parseInt(projectId), month, year);

		if (projectActionDetails.size() > 0) {
			List<Integer> userIds = projectActionDetails.stream().map(ProjectActionDetail::getCreatedBy)
					.collect(Collectors.toList());
			List<UserRoleAssociation> userDetails = userRoleAssociationRepository.findAllByUserIdIn(userIds);
			Map<Integer, UserRoleAssociation> userDataMap = prepareUserRelatedData(userDetails);
			for (ProjectActionDetail projectAction : projectActionDetails) {
				WorkflowTransectionDetailDTO workflowTransectionDetailDTO = new WorkflowTransectionDetailDTO();
				UserRoleAssociation userRoleAssociation = userDataMap.get(projectAction.getCreatedBy());
				workflowTransectionDetailDTO.setUserId(userRoleAssociation.getUserId());
				workflowTransectionDetailDTO.setUserName(userRoleAssociation.getUserName());
				workflowTransectionDetailDTO.setRoleId(userRoleAssociation.getRoleId());
				workflowTransectionDetailDTO.setRoleName(userRoleAssociation.getRoleName());
				workflowTransectionDetailDTO.setComment(projectAction.getComments());
				workflowTransectionDetailDTO.setActionId(projectAction.getActionId());
				if (projectAction.getActionId() == 1) {
					workflowTransectionDetailDTO.setStatus(SUBMITTED);
				} else if (projectAction.getActionId() == 2) {
					workflowTransectionDetailDTO.setStatus(APPROVED);
				} else {
					workflowTransectionDetailDTO.setStatus(RETURNED);
				}
				workflowTransectionDetailDTO.setCreatedDate(projectAction.getCreatedDate());
				workflowTransectionDetailDTOList.add(workflowTransectionDetailDTO);
			}
		}
		return workflowTransectionDetailDTOList;
	}

	private Map<Integer, UserRoleAssociation> prepareUserRelatedData(List<UserRoleAssociation> userDetails) {
      Map<Integer, UserRoleAssociation>  map = new HashMap<Integer, UserRoleAssociation>();
		
		for (UserRoleAssociation userRoleAssociation : userDetails) {
			map.put(userRoleAssociation.getUserId(), userRoleAssociation);
		}
		
		return map;
	}

	private List<WorkflowDetailsDTO> prepareWorkflowDetails(List<ProjectApprover> list) {
		List<WorkflowDetailsDTO> workflowDetailsDTOList = new ArrayList<WorkflowDetailsDTO>();
		List<ProjectApprover>ActiveApproverList = list.stream().filter(data->data.getStatusId() == 10).collect(Collectors.toList());
		List<Integer> userRoleIds = ActiveApproverList.stream().map(ProjectApprover::getRoleId).collect(Collectors.toList());
		List<Integer> approverIds = ActiveApproverList.stream().map(ProjectApprover::getApprovedBy).collect(Collectors.toList());
		List<UserRoleAssociation> userDetails = userRoleAssociationRepository.findAllByUserIdInAndRoleIdIn(approverIds,userRoleIds);
		Map<String,UserRoleAssociation> userAssoctionMap  = prepareUserRoleAssociationData(userDetails);
		String submissionFlag = "";
		ProjectApprover projectSubmitterData = list.get(0);
		if(projectSubmitterData.getApproveStatus() == 14) {
			submissionFlag ="Pending";
		}
		for(ProjectApprover projectApprover:ActiveApproverList) {
			WorkflowDetailsDTO workflowDetailsDTO = new WorkflowDetailsDTO();
			UserRoleAssociation userRoleAssociation = userAssoctionMap.get(projectApprover.getApprovedBy()+SEPERATOR+projectApprover.getRoleId());
			workflowDetailsDTO.setUserId(projectApprover.getApprovedBy());
			workflowDetailsDTO.setOrderId(projectApprover.getOrderId());
			workflowDetailsDTO.setRoleId(projectApprover.getRoleId());
			workflowDetailsDTO.setRoleName(projectApprover.getRoleName());
			workflowDetailsDTO.setStatusId(projectApprover.getStatusId());
			if(StringUtils.equalsIgnoreCase("Pending", submissionFlag)) {
				if(workflowDetailsDTO.getOrderId() != 0) {
					workflowDetailsDTO.setApproveStatus(GREY);
				}else {
					workflowDetailsDTO.setApproveStatus(YELLOW);
				}
			}else {
				if(workflowDetailsDTO.getOrderId() == 0) {
					workflowDetailsDTO.setApproveStatus(GREEN);
				}else {
					if(projectApprover.getApproveStatus() == 14) {                //14 = pending ,18 = approved
						workflowDetailsDTO.setApproveStatus(YELLOW);
					} else if(projectApprover.getApproveStatus() == 18) {
						workflowDetailsDTO.setApproveStatus(GREEN); 
					}else {
						workflowDetailsDTO.setApproveStatus(GREY); 
					}	
				}
			}
			if(userRoleAssociation != null) {
				workflowDetailsDTO.setUserName(userRoleAssociation.getUserName());
			}
			workflowDetailsDTOList.add(workflowDetailsDTO);
		}
		return workflowDetailsDTOList;
	}
}
