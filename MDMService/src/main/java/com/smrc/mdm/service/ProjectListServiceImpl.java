package com.smrc.mdm.service;

import java.math.BigDecimal;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mind.azure.storage.service.AzureBlobAdapter;
import com.smrc.mdm.model.CustomProjectProcessAssociation;
import com.smrc.mdm.model.CustomProjectTechnicalCenterAssociation;
import com.smrc.mdm.model.ProcessWithDate;
import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectByIdMapper;
import com.smrc.mdm.model.ProjectListMapper;
import com.smrc.mdm.model.ProjectProcessAssociation;
import com.smrc.mdm.model.ProjectTechnicalCenterAssociation;
import com.smrc.mdm.model.Unit;
import com.smrc.mdm.model.UserInfo;
import com.smrc.mdm.repository.ProjectProcessAssociationRepository;
import com.smrc.mdm.repository.ProjectRepository;
import com.smrc.mdm.repository.ProjectTechnicalCenterAssociationRepository;

@Service
public class ProjectListServiceImpl implements ProjectListService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectProcessAssociationRepository projectProcessAssociationRepository;
	
	@Autowired
	ProjectTechnicalCenterAssociationRepository projectTechnicalCenterAssociationRepository;
	
	@Autowired
	private AzureBlobAdapter azureBlobAdapter;

	@Value("${projectListPageSize}")
	private String pageSize;
	
	@Value("${defaultProjectLife}")
    private BigDecimal projectLife;
	
	private static String EMPTY = StringUtils.EMPTY;
	private static String START = "start";
	private static String END = "end";
	

	@Override
	public Page<ProjectListMapper> getAllProjectList() {

		Integer size = Integer.parseInt(pageSize);
		Pageable pageable = PageRequest.of(0, size);

		List<ProjectListMapper> projectListMapperList = projectRepository.findAllProjectListMapper();

		

		/*
		 * HashMap<Integer, List<String>> projectProcessListHashObj = hashMapConverter(
		 * customProjectProcessAssociationList);
		 */
		
		/* Getting CunstomProjectTechnicalCenter list */
		List<CustomProjectTechnicalCenterAssociation> customProjectTechnicalCenterAssociations = projectTechnicalCenterAssociationRepository
				.findAllCustomProjectTechnicalCenterAssociation();
		HashMap<String, List <String>> projectTechnicalCenterListHashObj = hashMapConverterForTechnicalCanter(customProjectTechnicalCenterAssociations);
		
		List<String>technicalCentrNameList = null;
		for (ProjectListMapper projectListMapper : projectListMapperList) {
			technicalCentrNameList = projectTechnicalCenterListHashObj.get(EMPTY+projectListMapper.getId());
			
			if(technicalCentrNameList != null && !technicalCentrNameList.isEmpty()) {
				projectListMapper.setTechnicalCenterNameList(technicalCentrNameList);
			}

		}
		
		
		
		int total = projectListMapperList.size();
		Page<ProjectListMapper> projectListMapperListPage = new PageImpl<>(projectListMapperList, pageable, total);

		return projectListMapperListPage;

	}
	// hashmapConverter for technical center list
	public HashMap<String, List<String>>hashMapConverterForTechnicalCanter(
			List<CustomProjectTechnicalCenterAssociation> customProjectTechnicalCenterAssociations){
		HashMap<String, List<String>> projectTechnicalCenterListHashObj = new HashMap<>();
		for (CustomProjectTechnicalCenterAssociation customProjectTechnicalCenter : customProjectTechnicalCenterAssociations) {

			String technicalCenterNameInProjectTechnicalAssn = customProjectTechnicalCenter.getName();
			if (!StringUtils.isEmpty(technicalCenterNameInProjectTechnicalAssn)) {

				List<String> technicalCenterNameList = projectTechnicalCenterListHashObj.get(EMPTY+customProjectTechnicalCenter.getId());
				if (technicalCenterNameList != null && !technicalCenterNameList.isEmpty()) {
					technicalCenterNameList.add(technicalCenterNameInProjectTechnicalAssn);
				projectTechnicalCenterListHashObj.put(EMPTY+customProjectTechnicalCenter.getId(), technicalCenterNameList);

				} else {
					ArrayList<String> technicalCenterNameListObj = new ArrayList<>();
					technicalCenterNameListObj.add(technicalCenterNameInProjectTechnicalAssn);
					projectTechnicalCenterListHashObj.put(EMPTY+customProjectTechnicalCenter.getId(), technicalCenterNameListObj);
				}
			}
		}
		return projectTechnicalCenterListHashObj;
	}

	@Override
	public ProjectByIdMapper getProjectById(Integer projectId) {

		ProjectByIdMapper projectByIdMapperObj = new ProjectByIdMapper();

		Optional<Project> optionalProject = projectRepository.findById(projectId);

		Project project = null;
		if (optionalProject.isPresent()) {
			project = optionalProject.get();
			projectByIdMapperObj.setProject(project);
			
			if(project.getProductFileMetaData() != null) {
				URI productImageUri = azureBlobAdapter.getBlobUri(project.getProductFileMetaData().getBlobName());
				projectByIdMapperObj.setProductImage(productImageUri);
			}
			
			if(project.getVehicleFileMetaData() != null) {
				URI vehicleImageUri = azureBlobAdapter.getBlobUri(project.getVehicleFileMetaData().getBlobName());
				projectByIdMapperObj.setVehicleImage(vehicleImageUri);
			}

		}

		// parent project
		Integer parentProjectId = project.getParentProjectId();
		if (parentProjectId != null) {
			Optional<Project> parentProject = projectRepository.findById(parentProjectId);
			if (parentProject.isPresent()) {
				projectByIdMapperObj.setParentProject(parentProject.get());
			}
		}
		
        // check project life
		/*
		 * if(projectByIdMapperObj.getProject().getProjectLife() == 0) {
		 * projectByIdMapperObj.getProject().setProjectLife(projectLife); }
		 */
          
		projectByIdMapperObj.setConvertedProjectLife(project.getProjectLife().toString());
		
		//projectByIdMapperObj.getProject().setProjectLife(project.getProjectLife());
		
		// getProcessWithDateList
		List<ProjectProcessAssociation> projectProcessAssociationList = projectProcessAssociationRepository
				.findByProjectId(projectId);
		List<ProcessWithDate> processWithDateList = getProcessWithDateList(projectProcessAssociationList);

		projectByIdMapperObj.setProcessWithDateList(processWithDateList);
		
		//projectManager and projectController
		if(projectProcessAssociationList !=null && !projectProcessAssociationList.isEmpty()) {
			UserInfo projectManager = projectProcessAssociationList.get(0).getProjectManager();
			UserInfo projectController = projectProcessAssociationList.get(0).getProjectController();
			projectByIdMapperObj.setProjectManager(projectManager);
			projectByIdMapperObj.setProjectController(projectController);
		}
		//technicalCenter List 
		
		List<ProjectTechnicalCenterAssociation> projectTechnicalCenterAssociationList = projectTechnicalCenterAssociationRepository
				.findByProjectId(projectId);
		List<Unit> technicalCenterList = projectTechnicalCenterAssociationList.stream()
				.map(projectTechnicalCenterAssn -> projectTechnicalCenterAssn.getUnit()).collect(Collectors.toList());
		projectByIdMapperObj.setTechnicalCenterList(technicalCenterList);
		return projectByIdMapperObj;
	}

	// getProcessWithDateList`
	public List<ProcessWithDate> getProcessWithDateList(List<ProjectProcessAssociation> projectProcessAssociationList) {
		List<ProcessWithDate> processWithDateList = new ArrayList<ProcessWithDate>();
		if (projectProcessAssociationList != null && !projectProcessAssociationList.isEmpty()) {
			for (ProjectProcessAssociation projectProcessAssociation : projectProcessAssociationList) {
				ProcessWithDate processWithDate = new ProcessWithDate();
				processWithDate.setProcess(projectProcessAssociation.getProcess());
				SimpleDateFormat dateformatter = new SimpleDateFormat("dd MMMM yyyy");

				Date startDate = null;
				try {
					if(projectProcessAssociation.getStartDate()!=null) {
						startDate = dateformatter.parse(projectProcessAssociation.getStartDate());
						processWithDate.setStartDate(startDate);
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				Date endDate = null;
				try {
					if(projectProcessAssociation.getEndDate() != null) {
						endDate = dateformatter.parse(projectProcessAssociation.getEndDate());
						processWithDate.setEndDate(endDate);	
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
				processWithDateList.add(processWithDate);

			}
		}
		return processWithDateList;
	}

	@Override
	public Project getProjectByProjectId(Integer projectId) {

		ProjectByIdMapper projectByIdMapperObj = new ProjectByIdMapper();

		Optional<Project> optionalProject = projectRepository.findById(projectId);

		Project project = null;
		if (optionalProject.isPresent()) {
			project = optionalProject.get();
			projectByIdMapperObj.setProject(project);
		}
		return project;
	}
	
}
