package com.smrc.gpor.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.dto.ProjectSubmissionDTO;
import com.smrc.gpor.model.ActionMaster;
import com.smrc.gpor.model.MailBox;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.ProjectActionDetail;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.model.ProjectProcessAssociation;
import com.smrc.gpor.model.ProjectSubmissionStatus;
import com.smrc.gpor.model.UserRoleAssociation;
import com.smrc.gpor.model.WorkflowDetails;
import com.smrc.gpor.repository.ActionMasterRepository;
import com.smrc.gpor.repository.CustomerGroupUserMappingRepository;
import com.smrc.gpor.repository.MailBoxRepository;
import com.smrc.gpor.repository.ProcessGpcMappingRepository;
import com.smrc.gpor.repository.ProjectActionDetailRepository;
import com.smrc.gpor.repository.ProjectApproverRepository;
import com.smrc.gpor.repository.ProjectProcessAssociationRepository;
import com.smrc.gpor.repository.ProjectRepository;
import com.smrc.gpor.repository.ProjectSubmissionStatusRepository;
import com.smrc.gpor.repository.UserRoleAssociationRepository;
import com.smrc.gpor.repository.WorkflowDetailRepository;
import com.smrc.gpor.service.EmailSenderservice;
import com.smrc.gpor.service.ProjectSubmissionService;

@Service
public class ProjectSubmissionServiceImpl implements ProjectSubmissionService {

	
	
	@Autowired
	private ProjectSubmissionStatusRepository projectSubmissionStatusRepository;
	
	@Autowired
	private ActionMasterRepository actionMasterRepository;
	
	@Autowired
	private ProjectActionDetailRepository projectActionDetailRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProcessGpcMappingRepository processGpcMappingRepository;
	
	@Autowired
	private CustomerGroupUserMappingRepository customerGroupUserMappingRepository;
	
	@Autowired
	private UserRoleAssociationRepository userRoleAssociationRepository;
	
	@Autowired
	private ProjectApproverRepository projectApproverRepository;
	@Autowired
	private SaveMailDataServiceImpl saveMailDataServiceImpl;

	@Autowired
	private  MailBoxRepository  mailBoxRepository ;
	
	@Autowired
	private WorkflowDetailRepository workflowDetailRepository;
	
	@Autowired
	private ProjectProcessAssociationRepository projetcAssociationRepository;
	
	@Autowired
	EmailSenderservice emailSenderservice;	
	
	private final Integer roleIdForGpc = 5;
	private final Integer roleIdForBuHead = 4;
	
	private static Integer ACTIVE = 10;
	 private static Integer DEACTIVE = 30;
	
	@Transactional
	@Override
	public String submitProject(ProjectSubmissionDTO projectSubmissionDTO,String userId) throws MessagingException {
		if(!(projectSubmissionDTO.getActionKey().equalsIgnoreCase("APPROVE_PROJECT"))) {
			ProjectSubmissionStatus projectSubmissionStatus = prepareProjectSubmissionStatus(projectSubmissionDTO,userId);
			projectSubmissionStatusRepository.save(projectSubmissionStatus);
			}
		
		ProjectActionDetail projectActionDetail = prepareProjectActionDetail(projectSubmissionDTO,userId);
		List<ProjectApprover> projectApproverDetails = prepareProjectApprover(projectSubmissionDTO,userId);
		projectActionDetailRepository.save(projectActionDetail);
		
		projectApproverDetails.forEach(singleData->projectApproverRepository.save(singleData));
		MailBox mailBox=prepareMailBox(projectSubmissionDTO);
		mailBoxRepository.save(mailBox);
		
		//send mail
		
		String[] toMail1 = new String[] { "Vaibhav.Dhawan@mind-infotech.com", "Himanshu.Arora@mind-infotech.com",
				"Rajeev.Mishra@mind-infotech.com", "anil.saw@mind-infotech.com" };
		try {
			emailSenderservice.sendSimpleEmail(toMail1,
					mailBox.getMailBody() == null ? "<html><body></body></html>" : mailBox.getMailBody(),
					projectSubmissionDTO.getActionKey());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private MailBox prepareMailBox(ProjectSubmissionDTO projectSubmissionDTO) {

		//StringBuilder getMailBody=saveMailDataServiceImpl.getMailBody(projectSubmissionDTO);

		String getMailBody=saveMailDataServiceImpl.getMailBody(projectSubmissionDTO).toString();

		 List<ProjectApprover> projectApproverList = projectApproverRepository.getApprovedBy(projectSubmissionDTO.getProjectId(),projectSubmissionDTO.getMonth(),projectSubmissionDTO.getYear(),14) ;
     String email="";
      if(projectApproverList!=null  &&  projectApproverList.size()!=0) {
    	  for(int i=0;i<projectApproverList.size();i++) {
    		  int targetUserId=projectApproverList.get(i).getApprovedBy();
    		  Map  UserEmailIdMap= userRoleAssociationRepository.getEmailId(targetUserId) ;
    		  if(i==0) {
    			 	if(UserEmailIdMap!=null)
    			   	  {email=UserEmailIdMap.get("userEmail")==null?"": UserEmailIdMap.get("userEmail").toString();}
    					 } 
    		  else {
    			  if(UserEmailIdMap!=null)
    			  email=","+UserEmailIdMap.get("userEmail")==null?"": UserEmailIdMap.get("userEmail").toString();
    		  }
    			 
    		  }
    	  }
    	  
   	 //Integer UserId= projectApproverList.get(0).getApprovedBy();
   //	Map  UserEmailIdMap= userRoleAssociationRepository.getEmailId(UserId) ;
  
    String projectDescription= actionMasterRepository.getDescription(projectSubmissionDTO.getActionKey());
       MailBox mailBox=new MailBox();
       if(email!="") {
		mailBox.setReferenceId(0);
		mailBox.setMessageId(0);
		mailBox.setTrial(0);
		mailBox.setHasAttachment("N");
		mailBox.setErrorSuccess("new");
		mailBox.setMailCreater(1);
		mailBox.setMailSubject(projectDescription);
		mailBox.setMailCreatedDate(projectSubmissionDTO.getUpdatedDate());
		mailBox.setMessageId(0);
		mailBox.setSourcePage(projectSubmissionDTO.getActionKey());
		mailBox.setMailCcId("administrator.mdm@mind-infotech.com");
		mailBox.setMailBccId("Garima.Khelani@mind-infotech.com");
		mailBox.setMailFromId("Garima.Khelani@mind-infotech.com");

		mailBox.setMailToId(email);
		mailBox.setMailBody(getMailBody);

       }
		return mailBox;
}
	private ProjectSubmissionStatus prepareProjectSubmissionStatus(ProjectSubmissionDTO projectSubmissionDTO,String userId) {
		ProjectSubmissionStatus projectSubmissionStatus = new ProjectSubmissionStatus();
		
		ProjectSubmissionStatus existingData= projectSubmissionStatusRepository.findDataByProjectIdMonthYear(projectSubmissionDTO.getProjectId(),
				projectSubmissionDTO.getMonth(),projectSubmissionDTO.getYear());
		if(existingData!=null) {
			projectSubmissionStatus=existingData;
		}
		if (!(projectSubmissionDTO.getActionKey().equalsIgnoreCase("APPROVE_PROJECT"))) {
			projectSubmissionStatus.setProjectId(projectSubmissionDTO.getProjectId());
			projectSubmissionStatus.setMonth(projectSubmissionDTO.getMonth());
			projectSubmissionStatus.setYear(projectSubmissionDTO.getYear());
			projectSubmissionStatus.setUpdatedDate(projectSubmissionDTO.getUpdatedDate());
			projectSubmissionStatus.setSubmittedDate(projectSubmissionDTO.getUpdatedDate());
			projectSubmissionStatus.setSubmissionFlag(projectSubmissionDTO.getSubmissionFlag());
			if(existingData!=null) {
				if((projectSubmissionDTO.getActionKey().equalsIgnoreCase("SUBMIT_PROJECT"))){
					projectSubmissionStatus.setSubmittedBy(Integer.parseInt(userId));
				}else {
					projectSubmissionStatus.setSubmittedBy(existingData.getSubmittedBy());
				}
			}
			else {
				projectSubmissionStatus.setSubmittedBy(Integer.parseInt(userId));
			}
		}
		return projectSubmissionStatus;
	}
	
	private ProjectActionDetail prepareProjectActionDetail(ProjectSubmissionDTO projectSubmissionDTO,String userId) {
		ProjectActionDetail projectActionDetail = new ProjectActionDetail();
		ActionMaster actionMaster = actionMasterRepository.getActionMasterByActionKey(projectSubmissionDTO.getActionKey());
		
		if(null != actionMaster) {
			projectActionDetail.setActionId(actionMaster.getId());
		}
		
//		ProjectActionDetail existingData=projectActionDetailRepository.findByProjectIdMonthYear(projectSubmissionDTO.getProjectId(),
//				projectSubmissionDTO.getMonth(),projectSubmissionDTO.getYear());
//		if(existingData!=null) {
//			projectActionDetail=existingData;
//		}
		projectActionDetail.setProjectId(projectSubmissionDTO.getProjectId());
		projectActionDetail.setMonth(projectSubmissionDTO.getMonth());		
		projectActionDetail.setYear(projectSubmissionDTO.getYear());
		projectActionDetail.setUpdatedDate(projectSubmissionDTO.getUpdatedDate());
		projectActionDetail.setCreatedDate(projectSubmissionDTO.getUpdatedDate());
		if (!(projectSubmissionDTO.getActionKey().equalsIgnoreCase("SUBMIT_PROJECT"))) {
		projectActionDetail.setComments(projectSubmissionDTO.getComments());}
		else
		{
			String userName=userRoleAssociationRepository.getUserName(Integer.parseInt(userId));
			projectActionDetail.setComments("Project Submitted By " +userName);
		}
		
		projectActionDetail.setCreatedBy(Integer.parseInt(userId));
		return projectActionDetail;
	}
	
	
	
	private List <ProjectApprover> prepareProjectApprover(ProjectSubmissionDTO projectSubmissionDTO,String userId) {
		Integer currentUserId = Integer.parseInt(userId);
		List<ProjectApprover>projectApproverList = new ArrayList<ProjectApprover>();
		ProjectApprover projectApproverObject = null;
		ProjectApprover existingData = null;
		//check existing Data only for approve or return 
				if (projectSubmissionDTO.getActionKey().equalsIgnoreCase("APPROVE_PROJECT")
						|| projectSubmissionDTO.getActionKey().equalsIgnoreCase("MARK_PROJECT_RE_EDIT")) {
					existingData = projectApproverRepository.findByProjectIdAndMonthAndYearAndApprovedByAndStatusId(projectSubmissionDTO.getProjectId(),
							projectSubmissionDTO.getMonth(), projectSubmissionDTO.getYear(), Integer.parseInt(userId),10);
		}
		if(projectSubmissionDTO.getActionKey().equalsIgnoreCase("APPROVE_PROJECT")) {
			
			projectApproverObject = new ProjectApprover();
			if(existingData != null) {
				projectApproverObject = existingData;
			}
			//call update next approver status from not started to pending
			Integer incrementedOrderId = projectApproverObject.getOrderId()+1; 
			ProjectApprover nextApproverObject = updateNextApproverStatus(incrementedOrderId,projectSubmissionDTO,projectApproverObject.getStatusId());
			projectApproverObject.setApproveStatus(18);
			projectApproverObject.setUpdatedBy(Integer.parseInt(userId));
			projectApproverObject.setUpdatedDate(projectSubmissionDTO.getUpdatedDate());
			projectApproverObject.setCreatedDate(projectSubmissionDTO.getUpdatedDate());
			projectApproverList.add(projectApproverObject);
			if(nextApproverObject != null) {
				projectApproverList.add(nextApproverObject);
			}
		}
        if(projectSubmissionDTO.getActionKey().equalsIgnoreCase("MARK_PROJECT_RE_EDIT")) { 
			List<ProjectApprover>activeProjectApproverList = projectApproverRepository.findAllByProjectIdAndMonthAndYearAndStatusId(projectSubmissionDTO.getProjectId(), projectSubmissionDTO.getMonth()
					, projectSubmissionDTO.getYear(),ACTIVE);
			for (ProjectApprover projectApprover : activeProjectApproverList) {
				projectApproverObject = new ProjectApprover();
				if (activeProjectApproverList.size() > 0) {
					projectApproverObject = projectApprover;
				}
				if (projectApproverObject.getApprovedBy() == currentUserId.intValue()) {
					projectApproverObject.setApproveStatus(16);
				}
				projectApproverObject.setUpdatedBy(currentUserId);
				projectApproverObject.setStatusId(DEACTIVE);
			}
			projectApproverList.add(projectApproverObject);
		}
		if(projectSubmissionDTO.getActionKey().equalsIgnoreCase("SUBMIT_PROJECT")) {
	//	Integer customerGroupId = projectRepository.findCustomerGroupIdById(projectSubmissionDTO.getProjectId());
		Integer programManagerId = projetcAssociationRepository.findProgramManagerIdByProjectId(projectSubmissionDTO.getProjectId());
		Integer programManagerUserId = userRoleAssociationRepository.findUserIdById(programManagerId);
		//Integer buHeadId = customerGroupUserMappingRepository.findByCustomerGroupId(customerGroupId);
		Integer gpcDetails = processGpcMappingRepository.findByProcessId(1);
		/* get workflow Master details */
		List<WorkflowDetails> workflowMasterDetails = workflowDetailRepository.findAll();
		/* get user name and Active roles */
		List<Integer>userIdList = new ArrayList<Integer>();
		userIdList.add(programManagerUserId);
	//	userIdList.add(buHeadId);
		userIdList.add(gpcDetails);
		/* get user role id from workflow details Model */
		List<Integer>userRoleIdList = workflowMasterDetails.stream().map(WorkflowDetails :: getRoleId).collect(Collectors.toList());
		List<UserRoleAssociation> userDetails = userRoleAssociationRepository.findAllByUserIdInAndRoleIdIn(userIdList,userRoleIdList);
		/* prepare order id by roleID */
		Map<Integer, WorkflowDetails> dataList = prepareOrderIdMapper(workflowMasterDetails);
		WorkflowDetails workflowDetails = null;
		for (UserRoleAssociation userData : userDetails) {
			projectApproverObject = new ProjectApprover();
			projectApproverObject.setProjectId(projectSubmissionDTO.getProjectId());
			projectApproverObject.setMonth(projectSubmissionDTO.getMonth());
			projectApproverObject.setYear(projectSubmissionDTO.getYear());
			projectApproverObject.setApprovedBy(userData.getUserId());
			projectApproverObject.setRoleId(userData.getRoleId());
			projectApproverObject.setRoleName(userData.getRoleName());
			workflowDetails = dataList.get(userData.getRoleId());
			projectApproverObject.setOrderId(workflowDetails.getOrderId());
			projectApproverObject.setCreatedBy(userData.getUserId());
			//set pending status for most recent approver
			if(workflowDetails.getOrderId() == 1) {
				projectApproverObject.setApproveStatus(14);
			}else {
				projectApproverObject.setApproveStatus(22); //22 = not started status for workflow.
			}
			
			projectApproverObject.setStatusId(ACTIVE);
			projectApproverList.add(projectApproverObject);
		}
	}
		//Sort object on the bases of order id
		projectApproverList.sort(Comparator.comparing(ProjectApprover :: getOrderId));
		return projectApproverList;
	}

	
	private Map<Integer, WorkflowDetails> prepareOrderIdMapper(List<WorkflowDetails> workflowMasterDetails) {
		HashMap<Integer, WorkflowDetails> workflowDetailDataMap = new HashMap<Integer, WorkflowDetails>();
		for(WorkflowDetails singleRecord:workflowMasterDetails) {
			if(singleRecord != null) {
				workflowDetailDataMap.put(singleRecord.getRoleId(), singleRecord);
			}
		}
		return workflowDetailDataMap;
	}


  private ProjectApprover updateNextApproverStatus(Integer incrementedOrderId, ProjectSubmissionDTO projectSubmissionDTO, Integer statusId) {
	  ProjectApprover projectApprover = null;
		Optional<ProjectApprover> nextApproverData = projectApproverRepository
				.findByProjectIdAndMonthAndYearAndOrderIdAndStatusId(projectSubmissionDTO.getProjectId(),
						projectSubmissionDTO.getMonth(), projectSubmissionDTO.getYear(), incrementedOrderId, statusId);
		if(nextApproverData.isPresent()) {
			projectApprover = new ProjectApprover();
			projectApprover= nextApproverData.get();
			//update status from not started to Pending.
			projectApprover.setApproveStatus(14);
		}
		  return projectApprover;
  }
	
	
	

}
