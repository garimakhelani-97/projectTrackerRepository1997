package com.smrc.mdm.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.comparator.Comparators;

import com.smrc.mdm.model.Brand;
import com.smrc.mdm.model.Currency;
import com.smrc.mdm.model.CustomerGroup;
import com.smrc.mdm.model.Process;
import com.smrc.mdm.model.Project;
import com.smrc.mdm.model.ProjectInputData;
import com.smrc.mdm.model.Status;
import com.smrc.mdm.model.Unit;
import com.smrc.mdm.model.UserInfo;
import com.smrc.mdm.model.UserRoleUnitAssociation;
import com.smrc.mdm.repository.BrandRepository;
import com.smrc.mdm.repository.CurrencyRepository;
import com.smrc.mdm.repository.CustomerGroupRepository;
import com.smrc.mdm.repository.ProcessRepository;
import com.smrc.mdm.repository.ProjectRepository;
import com.smrc.mdm.repository.StatusRepository;
import com.smrc.mdm.repository.UnitRepository;
import com.smrc.mdm.repository.UserInfoRepository;
import com.smrc.mdm.repository.UserRoleUnitAssociationRepository;

@Service
public class ProjectListInputDataServiceImpl implements ProjectListInputDataService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	ProcessRepository processRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	BrandRepository brandRepository;
	
	@Autowired
	CustomerGroupRepository customerGroupRepository;
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	@Autowired
	UserRoleUnitAssociationRepository userRoleUnitAssociationRepository;
	
	@Autowired
	CurrencyRepository currencyRepository;
	
	@Override
	public ProjectInputData getProjectListInputData() {

		ProjectInputData projectInputData = new ProjectInputData();

		List<Project> projectList = projectRepository.findAll();

		List<Unit> unitList = unitRepository.findAll();
		
		List<Status> statusList = statusRepository.findByStatusIgnoreCase("Project Status");
		
		List<Brand> brandList = brandRepository.findAll(); 
		
		List<CustomerGroup> customerGroupList = customerGroupRepository.findAll();
		
		List<Currency> currencyList = currencyRepository.findByRecordStatusId(10);

		// plant
        // sort list by plant name in Ascending order
		unitList.sort(Comparator.comparing(Unit::getDescription,String::compareToIgnoreCase));
		projectInputData.setPlantList(unitList);

		// parentProjectNameList
		List<Project> projectWithName = projectList.stream()
				.filter(project -> project.getName() != null && (project.getRecordStatusID()==null? 1:project.getRecordStatusID())==1 && !StringUtils.isEmpty(project.getName()))
				.sorted(Comparator.comparing(Project::getName,String::compareToIgnoreCase))
				.collect(Collectors.toList());
		projectInputData.setProjectList(projectWithName);
		
		// customer Group
		projectInputData.setCustomerGroupList(customerGroupList);


		// projectStatus
		 projectInputData.setStatusList(statusList);
		 
		 //brandList
		 
		 projectInputData.setBrandList(brandList);
		 
		 //currencyList
		 projectInputData.setCurrencyList(currencyList);

		 //techniCalCenterList
			List<Unit> technicalCenterList = unitList.stream().filter(
					unit -> unit.getIsTechnicalCenter() != null && Integer.parseInt(unit.getIsTechnicalCenter()) == 1)
					.sorted(Comparator.comparing(Unit::getDescription,String::compareToIgnoreCase))
					.collect(Collectors.toList());
			projectInputData.setTechnicalCentreList(technicalCenterList);

		
		return projectInputData;
	}

}
