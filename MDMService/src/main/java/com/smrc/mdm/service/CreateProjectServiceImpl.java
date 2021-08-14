package com.smrc.mdm.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.mdm.model.Process;
import com.smrc.mdm.model.ProcessWithDate;
import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectMapper;
import com.smrc.mdm.model.ProjectProcessAssociation;
import com.smrc.mdm.model.ProjectTechnicalCenterAssociation;
import com.smrc.mdm.model.Unit;
import com.smrc.mdm.repository.ProjectProcessAssociationRepository;
import com.smrc.mdm.repository.ProjectRepository;
import com.smrc.mdm.repository.ProjectTechnicalCenterAssociationRepository;

@Service
public class CreateProjectServiceImpl implements CreateProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectProcessAssociationRepository projectProcessAssociationRepository;
	
	@Autowired
	ProjectTechnicalCenterAssociationRepository projectTechnicalCenterAssociationRepository;

	@Autowired
	private SyncGPORServiceImlp syncGPORServiceImlp;
	@Autowired
	private SyncGateOneServiceImpl syncGateOneServiceImpl;
	@Override
	public String createProject(ProjectMapper projectMapper) {

		Project project = new Project();
		project.setName(projectMapper.getName());
		if (projectMapper.getParentProject() != null) {
			project.setParentProjectId(projectMapper.getParentProject().getId());
		}

		project.setDescription(projectMapper.getDescription());
		project.setCustomerGroup(projectMapper.getCustomerGroup());
		project.setCurrency(projectMapper.getCurrency());
		project.setBrand(projectMapper.getBrand());
		if (projectMapper.getBrand() != null) {
			project.setCustomer(projectMapper.getBrand().getCustomer());
		}

		project.setUnit(projectMapper.getPlant());

		// sopDate
		if (projectMapper.getSopdate() != null) {
			SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
			String sopDate = sopDateFormatter.format(projectMapper.getSopdate());
			String[] sopDateList = sopDate.split(" ");

			project.setSopMonth(sopDateList[1]);
			project.setSopYear(Integer.parseInt(sopDateList[2]));
		}
		// ispSopDate
//		if (projectMapper.getIspsopDate() != null) {
//			SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//			String ispsopDate = sopDateFormatter.format(projectMapper.getIspsopDate());
//			String[] ispSopDateList = ispsopDate.split(" ");
//
//			project.setSopMonth(ispSopDateList[1]);
//			project.setSopYear(Integer.parseInt(ispSopDateList[2]));
//		}

		// awardDate
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		if (projectMapper.getAwardDate() != null) {
			String awardDate = formatter.format(projectMapper.getAwardDate());
			project.setAwardDate(awardDate);
		}
		// ispAwardDate
		if (projectMapper.getIspAwardDate() != null) {
			String ispAwardDate = formatter.format(projectMapper.getIspAwardDate());
			project.setAwardDate(ispAwardDate);
		}

		project.setProjectLife(projectMapper.getProjectLife());
		project.setRecordStatusID(projectMapper.getRecordStatusId());
		project.setStatus(projectMapper.getProjectStatus());
		// project.setCreatedBy(projectMapper.getUserId().toString());

		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());

		project.setCreatedDate(createdDate);
		project.setIpAddress(projectMapper.getIpAddress());

		// setProjectId
		// format -> M + id + _ + dd + mm + yy
		Project projectObj = projectRepository.findTopByOrderByIdDesc();
		Integer lastRecordId = projectObj.getId() + 1;
		String dateValue[] = LocalDate.now().toString().split("-");
		String projectID = "M" + lastRecordId + "_" + dateValue[2] + "" + dateValue[1] + ""
				+ Integer.parseInt(dateValue[0]) % 100;
		project.setProjectId(projectID);

		Integer projectId = 0;
		//List<ProcessWithDate> processWithDateList = projectMapper.getProcessWithDateList();
		try {
			Project projectEntity = projectRepository.save(project);
			projectId = projectEntity.getId();

			/*
			 * if (!processWithDateList.isEmpty()) { for (ProcessWithDate processWithDate :
			 * processWithDateList) { Process process = processWithDate.getProcess(); if
			 * (process != null) { ProjectProcessAssociation projectProcessAssociation = new
			 * ProjectProcessAssociation(); projectProcessAssociation.setProcess(process);
			 * projectProcessAssociation.setProject(projectEntity); //
			 * projectProcessAssociation.setCreatedBy();
			 * projectProcessAssociation.setCreatedDate(createdDate);
			 * projectProcessAssociation.setRecordStatusId(projectMapper.getRecordStatusId()
			 * ); projectProcessAssociation.setIpAddress(projectMapper.getIpAddress());
			 * 
			 * SimpleDateFormat processDateformatter = new SimpleDateFormat("dd MMMM yyyy");
			 * if (processWithDate.getStartDate() != null) { String startDate =
			 * processDateformatter.format(processWithDate.getStartDate());
			 * projectProcessAssociation.setStartDate(startDate); } if
			 * (processWithDate.getEndDate() != null) { String endDate =
			 * processDateformatter.format(processWithDate.getEndDate());
			 * projectProcessAssociation.setEndDate(endDate); }
			 * 
			 * if (projectMapper.getProjectController() != null) { projectProcessAssociation
			 * .setProjectController(projectMapper.getProjectController()); }
			 * 
			 * if (projectMapper.getProjectManager() != null) {
			 * projectProcessAssociation.setProjectManager(projectMapper.getProjectManager()
			 * ); } ProjectProcessAssociation saveEntityProjectProcessAssociation =
			 * projectProcessAssociationRepository.save(projectProcessAssociation);
			 * if(process.getId().equals(1)) { String response =
			 * syncGPORServiceImlp.postRequestForGPOR(projectMapper,projectID,
			 * saveEntityProjectProcessAssociation); } } } } else if
			 * (projectMapper.getProjectController() != null ||
			 * projectMapper.getProjectManager() != null) {
			 * 
			 * ProjectProcessAssociation projectProcessAssociation = new
			 * ProjectProcessAssociation();
			 * 
			 * projectProcessAssociation.setProject(projectEntity); //
			 * projectProcessAssociation.setCreatedBy();
			 * projectProcessAssociation.setCreatedDate(createdDate);
			 * projectProcessAssociation.setRecordStatusId(projectMapper.getRecordStatusId()
			 * ); projectProcessAssociation.setIpAddress(projectMapper.getIpAddress());
			 * 
			 * if (projectMapper.getProjectController() != null) {
			 * projectProcessAssociation.setProjectController(projectMapper.
			 * getProjectController()); }
			 * 
			 * if (projectMapper.getProjectManager() != null) {
			 * projectProcessAssociation.setProjectManager(projectMapper.getProjectManager()
			 * ); } projectProcessAssociationRepository.save(projectProcessAssociation);
			 * 
			 * }
			 */
			
			//technical center
			List<Unit> technicalCentreList = projectMapper.getTechnicalCentre();
			if (technicalCentreList != null && !technicalCentreList.isEmpty()) {
				for (int i = 0; i < technicalCentreList.size(); i++) {
					ProjectTechnicalCenterAssociation projectTechnicalCenterAssociation = new ProjectTechnicalCenterAssociation();
					projectTechnicalCenterAssociation.setProjectId(projectId);
					projectTechnicalCenterAssociation.setUnit(technicalCentreList.get(i));
					projectTechnicalCenterAssociation.setRecordStatusId(1);
					projectTechnicalCenterAssociation.setCreatedBy(projectMapper.getUserId());
					projectTechnicalCenterAssociation.setCreatedDate(createdDate);
					projectTechnicalCenterAssociation.setIpAddress(projectMapper.getIpAddress());
					
					projectTechnicalCenterAssociationRepository.save(projectTechnicalCenterAssociation);
				}
			}
			
		} catch (Exception e) {
			System.out.println("EXCEPTION : " + e);
		}
		String response =syncGateOneServiceImpl.addProjectRequestForGateOne(projectMapper,projectID);
		return projectId.toString();
	
	}

}
