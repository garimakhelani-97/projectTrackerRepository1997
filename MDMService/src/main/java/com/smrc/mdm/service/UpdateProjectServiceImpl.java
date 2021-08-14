package com.smrc.mdm.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.smrc.mdm.exception.EntityNotFoundException;
import com.smrc.mdm.exception.IdNotFoundException;
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
public class UpdateProjectServiceImpl implements UpdateProjectService {

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
	public String updateProject(ProjectMapper projectMapper) {
		Boolean existingGPORProcess = Boolean.valueOf(false);
		Integer projectId = projectMapper.getId();
		if (projectId == null)
		throw new IdNotFoundException("Id not found");
		Optional<Project> projectOptional = this.projectRepository.findById(projectId);
		if (!projectOptional.isPresent())
		throw new EntityNotFoundException("Entity does not exist");
		Project project = projectOptional.get();
		Optional<ProjectProcessAssociation> optionalProjectprocess = project.getProjectProcessAssociationList().stream().filter(x -> (x.getProcess() != null && x.getProcess().getId().intValue() == 1)).findFirst();
		if (optionalProjectprocess.isPresent())
		existingGPORProcess = Boolean.valueOf(true);
		project.setName(projectMapper.getName());
		if (projectMapper.getParentProject() != null) {
		project.setParentProjectId(projectMapper.getParentProject().getId());
		} else {
		project.setParentProjectId(null);
		}
		project.setDescription(projectMapper.getDescription());
		project.setCustomerGroup(projectMapper.getCustomerGroup());
		project.setCurrency(projectMapper.getCurrency());
		project.setBrand(projectMapper.getBrand());
		if (projectMapper.getBrand() != null)
		project.setCustomer(projectMapper.getBrand().getCustomer());
		project.setUnit(projectMapper.getPlant());
		if (projectMapper.getSopdate() != null) {
		SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
		String sopDate = sopDateFormatter.format(projectMapper.getSopdate());
		String[] sopDateList = sopDate.split(" ");
		project.setSopMonth(sopDateList[1]);
		project.setSopYear(Integer.valueOf(Integer.parseInt(sopDateList[2])));
		}
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		if (projectMapper.getAwardDate() != null) {
		String awardDate = formatter.format(projectMapper.getAwardDate());
		project.setAwardDate(awardDate);
		}
		project.setProjectLife(projectMapper.getProjectLife());
		project.setRecordStatusID(projectMapper.getRecordStatusId());
		project.setStatus(projectMapper.getProjectStatus());
		project.setUpdatedBy(projectMapper.getUserId().toString());
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String updatedDate = formatter.format(new Date());
		project.setUpdatedDate(updatedDate);
		project.setIpAddress(projectMapper.getIpAddress());
		/*
		 * List<ProcessWithDate> ProcessWithDateList =
		 * projectMapper.getProcessWithDateList(); try {
		 * this.projectRepository.save(project); if (!ProcessWithDateList.isEmpty()) {
		 * this.projectProcessAssociationRepository.deleteByProjectId(projectId); for
		 * (ProcessWithDate processWithDate : ProcessWithDateList) { Process process =
		 * processWithDate.getProcess(); if (process != null) {
		 * ProjectProcessAssociation projectProcessAssociationObj = new
		 * ProjectProcessAssociation();
		 * projectProcessAssociationObj.setProcess(process);
		 * projectProcessAssociationObj.setProject(project);
		 * projectProcessAssociationObj.setCreatedDate(updatedDate);
		 * projectProcessAssociationObj.setCreatedBy(projectMapper.getUserId().toString(
		 * )); projectProcessAssociationObj.setRecordStatusId(projectMapper.
		 * getRecordStatusId());
		 * projectProcessAssociationObj.setIpAddress(projectMapper.getIpAddress());
		 * projectProcessAssociationObj.setProjectManager(projectMapper.
		 * getProjectManager());
		 * projectProcessAssociationObj.setProjectController(projectMapper.
		 * getProjectController()); SimpleDateFormat processDateformatter = new
		 * SimpleDateFormat("dd MMMM yyyy"); if (processWithDate.getStartDate() != null)
		 * { String strartDate =
		 * processDateformatter.format(processWithDate.getStartDate());
		 * projectProcessAssociationObj.setStartDate(strartDate); } if
		 * (processWithDate.getEndDate() != null) { String endDate =
		 * processDateformatter.format(processWithDate.getEndDate());
		 * projectProcessAssociationObj.setEndDate(endDate); } ProjectProcessAssociation
		 * saveEntityProjectProcessAssociation =
		 * (ProjectProcessAssociation)this.projectProcessAssociationRepository.save(
		 * projectProcessAssociationObj); System.out.println(projectMapper); if
		 * (process.getId().intValue() == 1 || existingGPORProcess.booleanValue()) { if
		 * (process.getId().intValue() != 1) { Optional<ProcessWithDate>
		 * GPORProcessExistingInList = ProcessWithDateList.stream().filter(x ->
		 * (x.getProcess() != null && x.getProcess().getId().intValue() ==
		 * 1)).findFirst(); if (!GPORProcessExistingInList.isPresent())
		 * existingGPORProcess = Boolean.valueOf(false); } else { existingGPORProcess =
		 * Boolean.valueOf(true); } String str =
		 * this.syncGPORServiceImlp.updateRequestForGPORProject(projectMapper,
		 * project.getProjectId(), existingGPORProcess,
		 * saveEntityProjectProcessAssociation); } } } } else if
		 * (projectMapper.getProjectController() != null ||
		 * projectMapper.getProjectManager() != null) {
		 * this.projectProcessAssociationRepository.deleteByProjectId(projectId);
		 * ProjectProcessAssociation projectProcessAssociationObj = new
		 * ProjectProcessAssociation();
		 * projectProcessAssociationObj.setProject(project);
		 * projectProcessAssociationObj.setCreatedDate(updatedDate);
		 * projectProcessAssociationObj.setCreatedBy(projectMapper.getUserId().toString(
		 * )); projectProcessAssociationObj.setRecordStatusId(projectMapper.
		 * getRecordStatusId());
		 * projectProcessAssociationObj.setIpAddress(projectMapper.getIpAddress()); if
		 * (projectMapper.getProjectManager() != null)
		 * projectProcessAssociationObj.setProjectManager(projectMapper.
		 * getProjectManager()); if (projectMapper.getProjectController() != null)
		 * projectProcessAssociationObj.setProjectController(projectMapper.
		 * getProjectController()); ProjectProcessAssociation
		 * saveEntityProjectProcessAssociation =
		 * (ProjectProcessAssociation)this.projectProcessAssociationRepository.save(
		 * projectProcessAssociationObj); if (existingGPORProcess.booleanValue()) {
		 * String str =
		 * this.syncGPORServiceImlp.updateRequestForGPORProject(projectMapper,
		 * project.getProjectId(), Boolean.valueOf(false),
		 * saveEntityProjectProcessAssociation);} }
		 */
		List<Unit> technicalCentreList = projectMapper.getTechnicalCentre();
		if (technicalCentreList != null && !technicalCentreList.isEmpty()) {
		this.projectTechnicalCenterAssociationRepository.deleteByProjectId(projectId);
		for (int i = 0; i < technicalCentreList.size(); i++) {
		ProjectTechnicalCenterAssociation projectTechnicalCenterAssociation = new ProjectTechnicalCenterAssociation();
		projectTechnicalCenterAssociation.setProjectId(projectId);
		projectTechnicalCenterAssociation.setUnit(technicalCentreList.get(i));
		projectTechnicalCenterAssociation.setRecordStatusId(Integer.valueOf(1));
		projectTechnicalCenterAssociation.setUpdatedBy(projectMapper.getUserId());
		projectTechnicalCenterAssociation.setUpdatedDate(updatedDate);
		projectTechnicalCenterAssociation.setIpAddress(projectMapper.getIpAddress());
		this.projectTechnicalCenterAssociationRepository.save(projectTechnicalCenterAssociation);
		}
		}
//		} catch (Exception exception) {}
		String response =syncGateOneServiceImpl.addProjectRequestForGateOne(projectMapper,project.getProjectId());
		return projectId.toString();
		}
	/*public String updateProject(ProjectMapper projectMapper) {

		Boolean existingGPORProcess=false;
		Integer projectId = projectMapper.getId();
		if (projectId == null) {
			throw new IdNotFoundException("Id not found");

		}
		Optional<Project> projectOptional = projectRepository.findById(projectId);
		if (!projectOptional.isPresent()) {
			throw new EntityNotFoundException("Entity does not exist");
		}
		Project project = projectOptional.get();
//		Optional<ProjectProcessAssociation> optionalProjectprocess = project.getProjectProcessAssociationList().stream()
//				.filter(x -> x.getProcess()!=null && x.getProcess().getId() == 1).findFirst();
//		if(optionalProjectprocess.isPresent()) {
//			existingGPORProcess=true;
//		}
		project.setName(projectMapper.getName());
		if (projectMapper.getParentProject() != null) {
			project.setParentProjectId(projectMapper.getParentProject().getId());
		}else {
			project.setParentProjectId(null);
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
		if (projectMapper.getIspsopDate() != null) {
			SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
			String ispSopDate = sopDateFormatter.format(projectMapper.getIspsopDate());
			String[] ispSopDateList = ispSopDate.split(" ");

			project.setIspSopMonth(ispSopDateList[1]);
			project.setIspSopYear(Integer.parseInt(ispSopDateList[2]));
		}

		// awardDate
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");

		if (projectMapper.getAwardDate() != null) {
			String awardDate = formatter.format(projectMapper.getAwardDate());
			project.setAwardDate(awardDate);
		}
		// ispAwardDate
		if (projectMapper.getIspAwardDate() != null) {
			String ispAwardDate = formatter.format(projectMapper.getIspAwardDate());
			project.setIspAwardDate(ispAwardDate);
		}

		project.setProjectLife(projectMapper.getProjectLife());
		project.setRecordStatusID(projectMapper.getRecordStatusId());

		project.setStatus(projectMapper.getProjectStatus());

		// user
		project.setUpdatedBy(projectMapper.getUserId().toString());

		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String updatedDate = formatter.format(new Date());

		project.setUpdatedDate(updatedDate);
		project.setIpAddress(projectMapper.getIpAddress());

		List<ProcessWithDate> ProcessWithDateList = projectMapper.getProcessWithDateList();

		try {
			projectRepository.save(project);

			if (!ProcessWithDateList.isEmpty()) {

				projectProcessAssociationRepository.deleteByProjectId(projectId);

				for (ProcessWithDate processWithDate : ProcessWithDateList) {

					Process process = processWithDate.getProcess();

					if (process != null) {

						ProjectProcessAssociation projectProcessAssociationObj = new ProjectProcessAssociation();
						projectProcessAssociationObj.setProcess(process);
						projectProcessAssociationObj.setProject(project);
						projectProcessAssociationObj.setCreatedDate(updatedDate);
						projectProcessAssociationObj.setCreatedBy(projectMapper.getUserId().toString());
						projectProcessAssociationObj.setRecordStatusId(projectMapper.getRecordStatusId());
						projectProcessAssociationObj.setIpAddress(projectMapper.getIpAddress());
						projectProcessAssociationObj.setProjectManager(projectMapper.getProjectManager());
						projectProcessAssociationObj.setProjectController(projectMapper.getProjectController());

						SimpleDateFormat processDateformatter = new SimpleDateFormat("dd MMMM yyyy");
						if (processWithDate.getStartDate() != null) {
							String strartDate = processDateformatter.format(processWithDate.getStartDate());
							projectProcessAssociationObj.setStartDate(strartDate);
						}
						if (processWithDate.getEndDate() != null) {
							String endDate = processDateformatter.format(processWithDate.getEndDate());
							projectProcessAssociationObj.setEndDate(endDate);
						}

						ProjectProcessAssociation saveEntityProjectProcessAssociation = projectProcessAssociationRepository.save(projectProcessAssociationObj);
						System.out.println("projectMapper"+projectMapper);
						System.out.println("outer"+ process.getId()+existingGPORProcess);
						if(process.getId()==1) {
//							System.out.println("inner"+ process.getId()+existingGPORProcess);
//							if(process.getId()!=1) {
//							Optional<ProcessWithDate> GPORProcessExistingInList = 
//									ProcessWithDateList.stream().filter(x-> x.getProcess()!=null &&x.getProcess().getId()==1).findFirst(); 
//							
//							if(!GPORProcessExistingInList.isPresent())
//							{existingGPORProcess=false;}
//							}
//							else {
//								existingGPORProcess=true;
//							}
							existingGPORProcess=true;
							String response = syncGPORServiceImlp.updateRequestForGPORProject(projectMapper,project.getProjectId(),existingGPORProcess,saveEntityProjectProcessAssociation);
							System.out.println("responseRestTemplate"+response);
						}

					}

				}
			} else if (projectMapper.getProjectController() != null || projectMapper.getProjectManager() != null) {
				
				projectProcessAssociationRepository.deleteByProjectId(projectId);
				
				ProjectProcessAssociation projectProcessAssociationObj = new ProjectProcessAssociation();

				projectProcessAssociationObj.setProject(project);
				projectProcessAssociationObj.setCreatedDate(updatedDate);
				projectProcessAssociationObj.setCreatedBy(projectMapper.getUserId().toString());
				projectProcessAssociationObj.setRecordStatusId(projectMapper.getRecordStatusId());
				projectProcessAssociationObj.setIpAddress(projectMapper.getIpAddress());
				if (projectMapper.getProjectManager() != null) {
					projectProcessAssociationObj.setProjectManager(projectMapper.getProjectManager());
				}
				if (projectMapper.getProjectController() != null) {
					projectProcessAssociationObj.setProjectController(projectMapper.getProjectController());
				}

				ProjectProcessAssociation saveEntityProjectProcessAssociation = projectProcessAssociationRepository.save(projectProcessAssociationObj);
//				if(existingGPORProcess) {
//					String response = syncGPORServiceImlp.updateRequestForGPORProject(projectMapper,project.getProjectId(),false,saveEntityProjectProcessAssociation);
//				}
			}
			//technical center
			List<Unit> technicalCentreList = projectMapper.getTechnicalCentre();
			if (technicalCentreList != null && !technicalCentreList.isEmpty()) {

				projectTechnicalCenterAssociationRepository.deleteByProjectId(projectId);

				for (int i = 0; i < technicalCentreList.size(); i++) {

					ProjectTechnicalCenterAssociation projectTechnicalCenterAssociation = new ProjectTechnicalCenterAssociation();
					projectTechnicalCenterAssociation.setProjectId(projectId);
					projectTechnicalCenterAssociation.setUnit(technicalCentreList.get(i));
					projectTechnicalCenterAssociation.setRecordStatusId(1);
					projectTechnicalCenterAssociation.setUpdatedBy(projectMapper.getUserId());
					projectTechnicalCenterAssociation.setUpdatedDate(updatedDate);
					projectTechnicalCenterAssociation.setIpAddress(projectMapper.getIpAddress());

					projectTechnicalCenterAssociationRepository.save(projectTechnicalCenterAssociation);
				}
			}

		} catch (Exception e) {

		}

		return projectId.toString();
	}*/

}
