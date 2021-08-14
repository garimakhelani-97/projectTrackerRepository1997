package com.smrc.gpor.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.model.AccountGateDetails;
import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.PipFinancials;
import com.smrc.gpor.model.PipTask;
//import com.smrc.gpor.model.Process;
//import com.smrc.mdm.model.ProcessWithDate;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.ProjectGateDetail;
import com.smrc.gpor.model.ProjectMetricsDetail;
import com.smrc.gpor.model.ProjectProcessAssociation;
import com.smrc.gpor.repository.AccountGateDetailsRepository;
import com.smrc.gpor.repository.PipActionRepository;
import com.smrc.gpor.repository.PipFinancialsRepository;
import com.smrc.gpor.repository.ProjectGateDetailRepository;
import com.smrc.gpor.repository.ProjectMetricsDetailRepository;
import com.smrc.gpor.repository.ProjectProcessAssociationRepository;
import com.smrc.gpor.repository.ProjectRepository;

@Service
public class SyncServiceImpl {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private ProjectProcessAssociationRepository projectProcessAssociationRepository;
	
	public String addProject(Map projectParameter,String projectId,Map projectProcessAssociation ) {
		System.out.println(projectParameter.get("processWithDateList"));
		Map brandMap = (Map) projectParameter.get("brand");
		Map parentProjectMap = (Map) projectParameter.get("parentProject");
		String projectRefId = projectId;
		String name = projectParameter.get("name") == null ? "" : projectParameter.get("name").toString();
		String customerGroupId = ((Map) projectParameter.get("customerGroup")).get("id") == null ? ""
				: ((Map) projectParameter.get("customerGroup")).get("id").toString();
		String customerGroupName = ((Map) projectParameter.get("customerGroup")).get("name") == null ? ""
				: ((Map) projectParameter.get("customerGroup")).get("name").toString();
		String brandId = ((Map) projectParameter.get("brand")).get("id") == null ? ""
				: ((Map) projectParameter.get("brand")).get("id").toString();
		String brandName = ((Map) projectParameter.get("brand")).get("name") == null ? ""
				: ((Map) projectParameter.get("brand")).get("name").toString();
		String customerId = ((Map) brandMap.get("customer")).get("id") == null ? ""
				: ((Map) brandMap.get("customer")).get("id").toString();
		String customerName = ((Map) brandMap.get("customer")).get("name") == null ? ""
				: ((Map) brandMap.get("customer")).get("name").toString();
		String unitId = ((Map) projectParameter.get("plant")).get("id") == null ? ""
				:((Map) projectParameter.get("plant")).get("id").toString();
		String unitname = ((Map) projectParameter.get("plant")).get("name") == null ? ""
				:((Map) projectParameter.get("plant")).get("name").toString();
		String unitDescription = ((Map) projectParameter.get("plant")).get("description") == null ? ""
				:((Map) projectParameter.get("plant")).get("description").toString();
		
		String projectStatusId = ((Map) projectParameter.get("projectStatus")).get("id") == null ? ""
				: ((Map) projectParameter.get("projectStatus")).get("id").toString();
		Project entity = new Project();
		entity.setProjectRefId(projectRefId);
		entity.setName(name);
		entity.setCustomerGroupId(Integer.parseInt(customerGroupId));
		entity.setCustomerGroupName(customerGroupName);
		entity.setBrandId(Integer.parseInt(brandId));
		entity.setBrandName(brandName);
		entity.setCustomerId(Integer.parseInt(customerId));
		entity.setCustomerName(customerName);
		entity.setUnitId(Integer.parseInt(unitId));
		entity.setUnitName(unitname);
		entity.setUnitDescription(unitDescription);
		entity.setRecordStatusID(1);
		SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
		String sopDate = sopDateFormatter.format(projectParameter.get("sopdate"));
		String[] sopDateList = sopDate.split(" ");
		
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		String awardDate = formatter.format(projectParameter.get("awardDate"));
		entity.setAwardDate(awardDate);
		String projectLife= projectParameter.get("projectLife").toString();

		entity.setProjectLife(projectLife);
		entity.setSopMonth(sopDateList[1]);
		entity.setSopYear(Integer.parseInt(sopDateList[2]));
		entity.setProjectStatusId(Integer.parseInt(projectStatusId));
		Integer currencyId = ((Map) projectParameter.get("currency")).get("id") == null ? 0
				: Integer.parseInt(((Map) projectParameter.get("currency")).get("id").toString());
		String currencyName=((Map) projectParameter.get("currency")).get("name") == null ? ""
				: ((Map) projectParameter.get("currency")).get("name").toString();
		entity.setCurrenyId(currencyId);
		entity.setCurrencyName(currencyName);
//		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());
		entity.setCreatedDate(createdDate);
		Project saveEntity = projectRepository.save(entity);
		//save inproject process association
		SimpleDateFormat processDateformatter = new SimpleDateFormat("dd MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDatePPA = formatter.format(new Date());
		String startDate="";String endDate="";
		if (projectProcessAssociation.get("startDate")!=null)
			startDate = projectProcessAssociation.get("startDate").toString();
		if (projectProcessAssociation.get("endDate")!=null)
			endDate = projectProcessAssociation.get("endDate").toString();
		ProjectProcessAssociation ppa=new ProjectProcessAssociation();
		ppa.setProjectId(saveEntity.getId());
		ppa.setProcessId(Integer.parseInt(((Map)projectProcessAssociation.get("process")).get("id").toString()));
		ppa.setStartDate(startDate);
		ppa.setEndDate(endDate);
		ppa.setProjectControllerId(Integer.parseInt(((Map)projectProcessAssociation.get("projectController")).get("id").toString()));
		ppa.setProjectManagerId(Integer.parseInt(projectProcessAssociation.get("recordStatusId").toString()));
		ppa.setCreatedDate(createdDatePPA);
		projectProcessAssociationRepository.save(ppa);
		return "";
	}

	/*
	 * public String updateProject(Map projectParameter, String projectId, Boolean
	 * recordStatusID,Map projectProcessAssociation) {
	 * System.out.println(projectParameter); SimpleDateFormat formatter = new
	 * SimpleDateFormat("MMMM yyyy"); Map brandMap = (Map)
	 * projectParameter.get("brand"); Map parentProjectMap = (Map)
	 * projectParameter.get("parentProject"); String projectRefId = projectId;
	 * String name = projectParameter.get("name") == null ? "" :
	 * projectParameter.get("name").toString(); String customerGroupId = ((Map)
	 * projectParameter.get("customerGroup")).get("id") == null ? "" : ((Map)
	 * projectParameter.get("customerGroup")).get("id").toString(); String
	 * customerGroupName = ((Map) projectParameter.get("customerGroup")).get("name")
	 * == null ? "" : ((Map)
	 * projectParameter.get("customerGroup")).get("name").toString(); String brandId
	 * = ((Map) projectParameter.get("brand")).get("id") == null ? "" : ((Map)
	 * projectParameter.get("brand")).get("id").toString(); String brandName =
	 * ((Map) projectParameter.get("brand")).get("name") == null ? "" : ((Map)
	 * projectParameter.get("brand")).get("name").toString(); String customerId =
	 * ((Map) brandMap.get("customer")).get("id") == null ? "" : ((Map)
	 * brandMap.get("customer")).get("id").toString(); String customerName = ((Map)
	 * brandMap.get("customer")).get("name") == null ? "" : ((Map)
	 * brandMap.get("customer")).get("name").toString(); String unitId = ((Map)
	 * projectParameter.get("plant")).get("id") == null ? "" : ((Map)
	 * projectParameter.get("plant")).get("id").toString(); String unitname = ((Map)
	 * projectParameter.get("plant")).get("name") == null ? "" : ((Map)
	 * projectParameter.get("plant")).get("name").toString(); String unitDescription
	 * = ((Map) projectParameter.get("plant")).get("unitDescription") == null ? "" :
	 * ((Map) projectParameter.get("plant")).get("unitDescription").toString();
	 * String projectStatusId = ((Map)
	 * projectParameter.get("projectStatus")).get("id") == null ? "" : ((Map)
	 * projectParameter.get("projectStatus")).get("id").toString(); Project entity =
	 * new Project(); entity = projectRepository.findByprojectRefId(projectId); if
	 * (entity == null) entity = new Project();
	 * 
	 * 
	 * 
	 * entity.setProjectRefId(projectRefId); entity.setName(name);
	 * entity.setCustomerGroupId(Integer.parseInt(customerGroupId));
	 * entity.setCustomerGroupName(customerGroupName);
	 * entity.setBrandId(Integer.parseInt(brandId)); entity.setBrandName(brandName);
	 * entity.setCustomerId(Integer.parseInt(customerId));
	 * entity.setCustomerName(customerName);
	 * entity.setUnitId(Integer.parseInt(unitId)); entity.setUnitName(unitname);
	 * entity.setUnitDescription(unitDescription);
	 * 
	 * SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
	 * String sopDate = ""; if (projectParameter.get("sopdate") != null) { sopDate =
	 * sopDateFormatter.format(projectParameter.get("sopdate")); String[]
	 * sopDateList = sopDate.split(" "); entity.setSopMonth(sopDateList[1]);
	 * entity.setSopYear(Integer.parseInt(sopDateList[2])); }
	 * 
	 * if (projectParameter.get("awardDate") != null) { String awardDate =
	 * formatter.format(projectParameter.get("awardDate"));
	 * entity.setAwardDate(awardDate); } String projectLife =
	 * projectParameter.get("projectLife").toString();
	 * 
	 * entity.setProjectLife(Integer.parseInt(projectLife));
	 * 
	 * entity.setProjectStatusId(Integer.parseInt(projectStatusId)); Integer
	 * currencyId = ((Map) projectParameter.get("currency")).get("id") == null ? 0 :
	 * Integer.parseInt(((Map)
	 * projectParameter.get("currency")).get("id").toString()); String currencyName
	 * = ((Map) projectParameter.get("currency")).get("name") == null ? "" : ((Map)
	 * projectParameter.get("currency")).get("name").toString();
	 * entity.setCurrenyId(currencyId); entity.setCurrencyName(currencyName); //
	 * SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy"); if
	 * (recordStatusID) { entity.setRecordStatusID(1); } else {
	 * entity.setRecordStatusID(3); } formatter = new
	 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); String updatedDate =
	 * formatter.format(new Date()); entity.setUpdatedDate(updatedDate);
	 * 
	 * Project saveEntity =projectRepository.save(entity); //save inproject process
	 * association SimpleDateFormat processDateformatter = new
	 * SimpleDateFormat("dd MMMM yyyy"); formatter = new
	 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); String createdDatePPA =
	 * formatter.format(new Date()); String startDate="";String endDate="";
	 * List<ProjectProcessAssociation>
	 * existingData=projectProcessAssociationRepository.findByProjectId(saveEntity.
	 * getId());
	 * 
	 * if (projectProcessAssociation.get("startDate")!=null) startDate =
	 * projectProcessAssociation.get("startDate").toString(); if
	 * (projectProcessAssociation.get("endDate")!=null) endDate =
	 * projectProcessAssociation.get("endDate").toString();
	 * ProjectProcessAssociation ppa=new ProjectProcessAssociation();
	 * if(existingData.size()>0) { ppa=existingData.get(0); }
	 * ppa.setProjectId(saveEntity.getId());
	 * ppa.setProcessId(Integer.parseInt(((Map)projectProcessAssociation.get(
	 * "process")).get("id").toString())); ppa.setStartDate(startDate);
	 * ppa.setEndDate(endDate);
	 * ppa.setProjectControllerId(Integer.parseInt(((Map)projectProcessAssociation.
	 * get("projectController")).get("id").toString()));
	 * ppa.setProjectManagerId(Integer.parseInt(projectProcessAssociation.get(
	 * "recordStatusId").toString())); ppa.setCreatedDate(createdDatePPA);
	 * projectProcessAssociationRepository.save(ppa);
	 * 
	 * return ""; }
	 */
	public String updateProject(Map projectParameter, String projectId, Boolean recordStatusID,Map projectProcessAssociation) {
		System.out.println(projectParameter);
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		Map brandMap = (Map) projectParameter.get("brand");
		Map parentProjectMap = (Map) projectParameter.get("parentProject");
		String projectRefId = projectId;
		String name = projectParameter.get("name") == null ? "" : projectParameter.get("name").toString();
		String customerGroupId = ((Map) projectParameter.get("customerGroup")).get("id") == null ? ""
				: ((Map) projectParameter.get("customerGroup")).get("id").toString();
		String customerGroupName = ((Map) projectParameter.get("customerGroup")).get("name") == null ? ""
				: ((Map) projectParameter.get("customerGroup")).get("name").toString();
		String brandId = ((Map) projectParameter.get("brand")).get("id") == null ? ""
				: ((Map) projectParameter.get("brand")).get("id").toString();
		String brandName = ((Map) projectParameter.get("brand")).get("name") == null ? ""
				: ((Map) projectParameter.get("brand")).get("name").toString();
		String customerId = ((Map) brandMap.get("customer")).get("id") == null ? ""
				: ((Map) brandMap.get("customer")).get("id").toString();
		String customerName = ((Map) brandMap.get("customer")).get("name") == null ? ""
				: ((Map) brandMap.get("customer")).get("name").toString();
		String unitId = ((Map) projectParameter.get("plant")).get("id") == null ? ""
				: ((Map) projectParameter.get("plant")).get("id").toString();
		String unitname = ((Map) projectParameter.get("plant")).get("name") == null ? ""
				: ((Map) projectParameter.get("plant")).get("name").toString();
		String unitDescription = ((Map) projectParameter.get("plant")).get("description") == null ? ""
				: ((Map) projectParameter.get("plant")).get("description").toString();
		String projectStatusId = ((Map) projectParameter.get("projectStatus")).get("id") == null ? ""
				: ((Map) projectParameter.get("projectStatus")).get("id").toString();
		Project entity = new Project();
		entity = projectRepository.findByprojectRefId(projectId);
		if (entity == null)
			entity = new Project();



			entity.setProjectRefId(projectRefId);
			entity.setName(name);
			entity.setCustomerGroupId(Integer.parseInt(customerGroupId));
			entity.setCustomerGroupName(customerGroupName);
			entity.setBrandId(Integer.parseInt(brandId));
			entity.setBrandName(brandName);
			entity.setCustomerId(Integer.parseInt(customerId));
			entity.setCustomerName(customerName);
			entity.setUnitId(Integer.parseInt(unitId));
			entity.setUnitName(unitname);
			entity.setUnitDescription(unitDescription);
			
			SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
			String sopDate = "";
			if (projectParameter.get("sopdate") != null) {
				sopDate = sopDateFormatter.format(projectParameter.get("sopdate"));
				String[] sopDateList = sopDate.split(" ");
				entity.setSopMonth(sopDateList[1]);
				entity.setSopYear(Integer.parseInt(sopDateList[2]));
			}

			if (projectParameter.get("awardDate") != null) {
				String awardDate = formatter.format(projectParameter.get("awardDate"));
				entity.setAwardDate(awardDate);
			}
			String projectLife = projectParameter.get("projectLife").toString();

			entity.setProjectLife(projectLife);

			entity.setProjectStatusId(Integer.parseInt(projectStatusId));
			Integer currencyId = ((Map) projectParameter.get("currency")).get("id") == null ? 0
					: Integer.parseInt(((Map) projectParameter.get("currency")).get("id").toString());
			String currencyName = ((Map) projectParameter.get("currency")).get("name") == null ? ""
					: ((Map) projectParameter.get("currency")).get("name").toString();
			entity.setCurrenyId(currencyId);
			entity.setCurrencyName(currencyName);
//		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		if (recordStatusID) {
			entity.setRecordStatusID(1);
		}
		else {
			entity.setRecordStatusID(3);
		}
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String updatedDate = formatter.format(new Date());
		entity.setUpdatedDate(updatedDate);

		Project saveEntity =projectRepository.save(entity);
		//save inproject process association
				SimpleDateFormat processDateformatter = new SimpleDateFormat("dd MMMM yyyy");
				formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
				String createdDatePPA = formatter.format(new Date());
				String startDate="";String endDate="";
				List<ProjectProcessAssociation> existingData=projectProcessAssociationRepository.findByProjectId(saveEntity.getId());
				
				if (projectProcessAssociation.get("startDate")!=null)
					startDate = projectProcessAssociation.get("startDate").toString();
				if (projectProcessAssociation.get("endDate")!=null)
					endDate = projectProcessAssociation.get("endDate").toString();
				ProjectProcessAssociation ppa=new ProjectProcessAssociation();
				if(existingData.size()>0) {
					ppa=existingData.get(0);
				}
				ppa.setProjectId(saveEntity.getId());
				ppa.setProcessId(Integer.parseInt(((Map)projectProcessAssociation.get("process")).get("id").toString()));
				ppa.setStartDate(startDate);
				ppa.setEndDate(endDate);
				ppa.setProjectControllerId(Integer.parseInt(((Map)projectProcessAssociation.get("projectController")).get("id").toString()));
				ppa.setProjectManagerId(Integer.parseInt(projectProcessAssociation.get("recordStatusId").toString()));
				ppa.setCreatedDate(createdDatePPA);
				projectProcessAssociationRepository.save(ppa);
			
		return "";
	}
	
		
	public String updateProjectImages(Map<String, Object> projectParameter,String projectId) {
		Project project = new Project();
		project=projectRepository.findByprojectRefId(projectId);
		if(project!=null) {
			
			String productImageId= projectParameter.get("productImageId").toString();
			
			String productImageBlobName= projectParameter.get("productImageBlobName").toString();
			
			String vehicleImageId= projectParameter.get("vehicleImageId").toString();
			
			String vehicleImageBlobName= projectParameter.get("vehicleImageBlobName").toString();
			
			String uploadedDate= projectParameter.get("uploadedDate").toString();
			
			project.setProductImageId(Integer.parseInt(productImageId));
			project.setProductImageBlobName(productImageBlobName);
			project.setVehicleImageId(Integer.parseInt(vehicleImageId));
			project.setVehicleImageBlobName(vehicleImageBlobName);
			project.setUploadedDate(uploadedDate);
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			String updatedDate = formatter.format(new Date());
			project.setUpdatedDate(updatedDate);
			projectRepository.save(project);
		}
		return "";
	}

	
//	@Autowired 
//	private ProjectRepository projectRepository;
	@Transactional
	public String addProjectProcessAndTransactionsData(Map Parameter) throws ParseException {
		Map projectMap=(Map) Parameter.get("projectMapper");
		Map project_process_associationMap=(Map) Parameter.get("project_process_associationMapper");
		List<Map> accountGateDetailsList= (List<Map>) Parameter.get("accountGateDetailsMapper");
		List<Map> pipActionList=(List<Map>)Parameter.get("pipActionMapper");
		List<Map> pipFinancialsList=(List<Map>)Parameter.get("pipFinancialsMapper");
		List<Map> projectMetricsDetailList=(List<Map>)Parameter.get("projectMetricsDetail");
		List<Map> projectGateDetailsList=(List<Map>)Parameter.get("projectGateDetailsMapper");
		System.out.println(Parameter.get("projectMapper"));
		
		//create project//		
		Project projectEntity=new Project();
		Project existenceData= projectRepository.findByprojectRefId(projectMap.get("projectRefId").toString());
		if(existenceData!=null) {
			projectEntity=existenceData;
		}
		projectEntity.setProjectRefId(projectMap.get("projectRefId").toString());
		projectEntity.setName(projectMap.get("name")==null?"":projectMap.get("name").toString());
		projectEntity.setDescription(projectMap.get("description")==null?"":projectMap.get("description").toString());
		projectEntity.setCustomerGroupId(projectMap.get("customerGroupId")==null?0:Integer.parseInt(projectMap.get("customerGroupId").toString()));
		projectEntity.setCustomerGroupName(projectMap.get("customerGroupName")==null?"":projectMap.get("customerGroupName").toString());
		projectEntity.setBrandId(projectMap.get("brandId")==null?0:Integer.parseInt(projectMap.get("brandId").toString()));
		projectEntity.setBrandName(projectMap.get("brandName")==null?"":projectMap.get("brandName").toString());
		projectEntity.setCustomerId(projectMap.get("customerId")==null?0:Integer.parseInt(projectMap.get("customerId").toString()));
		projectEntity.setCustomerName(projectMap.get("customerName")==null?"":projectMap.get("customerName").toString());
		projectEntity.setUnitId(projectMap.get("unitId")==null?0:Integer.parseInt(projectMap.get("unitId").toString()));
		projectEntity.setUnitName(projectMap.get("unitName")==null?"":projectMap.get("unitName").toString());
		projectEntity.setUnitDescription(projectMap.get("unitDescription")==null?"":projectMap.get("unitDescription").toString());
		projectEntity.setSopMonth(projectMap.get("sopMonth")==null?"":projectMap.get("sopMonth").toString());
		projectEntity.setSopYear(projectMap.get("sopYear")==null?0:Integer.parseInt(projectMap.get("sopYear").toString()));
		projectEntity.setProjectLife(projectMap.get("projectLife")==null?"":projectMap.get("projectLife").toString());
		projectEntity.setProjectLife(projectMap.get("projectLife")==null?"":projectMap.get("projectLife").toString());
		projectEntity.setRecordStatusID(1);
		projectEntity.setAwardDate(projectMap.get("awardDate")==null?"":projectMap.get("awardDate").toString());
		projectEntity.setProjectStatusId(projectMap.get("projectStatusId")==null?0:Integer.parseInt(projectMap.get("projectStatusId").toString()));
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());
		projectEntity.setCreatedDate(createdDate);
		projectEntity.setUpdatedDate(createdDate);
		projectEntity.setCurrenyId(projectMap.get("currenyId")==null?0:Integer.parseInt(projectMap.get("currenyId").toString()));
		projectEntity.setCurrencyName(projectMap.get("currencyName")==null?"":projectMap.get("currencyName").toString());
		Project saveProjectEntity=projectRepository.save(projectEntity);
		//create project//
		
		//data entry in project process association//
		ProjectProcessAssociation processAssociationEntity=new ProjectProcessAssociation();
		List<ProjectProcessAssociation> existingProjectProcessEntity=(List<ProjectProcessAssociation>) projectProcessAssociationRepository
				.findByProjectId(saveProjectEntity.getId());
		if(existingProjectProcessEntity.size()>0) {
			processAssociationEntity=existingProjectProcessEntity.get(0);
		}
		processAssociationEntity.setProjectId(saveProjectEntity.getId());
		processAssociationEntity.setProcessId(1);
		processAssociationEntity.setProjectControllerId(project_process_associationMap.get("projectControllerId")==null?0:
														Integer.parseInt(project_process_associationMap.get("projectControllerId").toString())	);
		processAssociationEntity.setProjectManagerId(project_process_associationMap.get("projectManagerId")==null?0:
														Integer.parseInt(project_process_associationMap.get("projectManagerId").toString())	);
		processAssociationEntity.setProgramManagerId(project_process_associationMap.get("programManagerId")==null?0:
														Integer.parseInt(project_process_associationMap.get("programManagerId").toString())	);
		processAssociationEntity.setStartDate(project_process_associationMap.get("startDate")==null?"":project_process_associationMap.get("startDate").toString());
		processAssociationEntity.setEndDate(project_process_associationMap.get("endDate")==null?"":project_process_associationMap.get("endDate").toString());
		processAssociationEntity.setCreatedDate(createdDate);
		processAssociationEntity.setUpdatedDate(createdDate);
		processAssociationEntity.setRecordStatusId(1);
		projectProcessAssociationRepository.save(processAssociationEntity);
		//data entry in project process association//
		
		saveTransactionsData(accountGateDetailsList,pipActionList,pipFinancialsList,projectMetricsDetailList,projectGateDetailsList,
				project_process_associationMap.get("startDate").toString(),project_process_associationMap.get("endDate").toString(),saveProjectEntity.getId());
		return "";
		
		
	}
	@Autowired
	private AccountGateDetailsRepository accountGateDetailRepository;
	@Autowired
	private PipActionRepository pipActionRepository;
	@Autowired
	private PipFinancialsRepository pipFinancialsRepository;
	@Autowired
	private ProjectMetricsDetailRepository projectMetricsDetailRepository;
	@Autowired
	private ProjectGateDetailRepository projectGateDetailRepository;
	@Transactional
	public String saveTransactionsData(List<Map>accountGateDetailsList,List<Map>pipActionList,List<Map>pipFinancialsList
									,List<Map>projectMetricsDetailList,List<Map>projectGateDetailsList,String startDate,String EndDate,Integer projectId) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());
		/// save data in accountGateDetail table//
		List<AccountGateDetails> existenceData= accountGateDetailRepository.findDataExistance(projectId,startDate.split(" ")[1].substring(0,3),startDate.split(" ")[2]);
		if(existenceData.size()==0) {
		for(int i=0;i<accountGateDetailsList.size();i++) {
			AccountGateDetails accountGateDetail=new AccountGateDetails();
			Map accountGateDetailsColumn=accountGateDetailsList.get(i);
			accountGateDetail.setProjectId(projectId);
			accountGateDetail.setAccountCode(accountGateDetailsColumn.get("accountCode")==null?"":accountGateDetailsColumn.get("accountCode").toString());
			accountGateDetail.setMonth(startDate.split(" ")[1].substring(0,3));
			accountGateDetail.setYear(startDate.split(" ")[2]);
			accountGateDetail.setCommitGate(accountGateDetailsColumn.get("commitGate")==null?"":accountGateDetailsColumn.get("commitGate").toString());
			accountGateDetail.setValue(accountGateDetailsColumn.get("value")==null?"":accountGateDetailsColumn.get("value").toString());
			accountGateDetail.setComment(accountGateDetailsColumn.get("comment")==null?"":accountGateDetailsColumn.get("comment").toString());
			accountGateDetail.setIsFreezed(accountGateDetailsColumn.get("isFreezed")==null?0:Integer.parseInt(accountGateDetailsColumn.get("isFreezed").toString()));
			accountGateDetail.setMappingVariable(accountGateDetailsColumn.get("mappingVariable")==null?0:Integer.parseInt(accountGateDetailsColumn.get("mappingVariable").toString()));
			accountGateDetail.setCreatedDate(createdDate);
			accountGateDetail.setUpdatedDate(createdDate);
			accountGateDetailRepository.save(accountGateDetail);
			
		}
		}
		/// save data in accountGateDetail table//
		
		/// save data in pipactions table//
		List<PipAction> existingPipData= pipActionRepository.findDataAgainstProjectidMonthYear(projectId,
									startDate.split(" ")[1].substring(0,3),startDate.split(" ")[2]);
		if(existingPipData.size()==0) {
		for(int i=0;i<pipActionList.size();i++) {
			Map pipColumn=pipActionList.get(i);
			PipAction pipEntity=new PipAction();
			Optional<Project> project = projectRepository.findById(projectId);
			pipEntity.setProject(project.get());
			pipEntity.setPipaction(pipColumn.get("pipaction")==null?"":pipColumn.get("pipaction").toString());
			pipEntity.setLcVeh(pipColumn.get("lcVeh")==null?"":pipColumn.get("lcVeh").toString());
			pipEntity.setConflevel(pipColumn.get("conflevel")==null?"":pipColumn.get("conflevel").toString());
			pipEntity.setEbitImpact(pipColumn.get("ebitImpact")==null?"":pipColumn.get("ebitImpact").toString());
			pipEntity.setIrrImpact(pipColumn.get("irrImpact")==null?"":pipColumn.get("irrImpact").toString());
			pipEntity.setRoceImpact(pipColumn.get("roceImpact")==null?"":pipColumn.get("roceImpact").toString());
			pipEntity.setImplDate(pipColumn.get("implDate")==null?"":pipColumn.get("implDate").toString());
			pipEntity.setComment(pipColumn.get("comment")==null?"":pipColumn.get("comment").toString());
			pipEntity.setUpdatedDate(createdDate);
			pipEntity.setCreatedDate(createdDate);
			pipEntity.setMonth(startDate.split(" ")[1].substring(0,3));
			pipEntity.setYear(startDate.split(" ")[2]);
			
			Set<PipTask> pipTasksForPipAction = new HashSet<PipTask>();
			List<Map> pipTaskList=pipColumn.get("pipTasks")==null?new ArrayList<Map>(): (ArrayList<Map>) pipColumn.get("pipTasks"); 
			for(int j=0;j<pipTaskList.size();j++) {
				Map pipTaskColumn= pipTaskList.get(j);
				PipTask pipTaskEntity=new PipTask();
//				pipTaskEntity.setPipActionId(pipTaskColumn.get("pipActionId")==null?0:Integer.parseInt(pipTaskColumn.get("pipActionId").toString()));
				pipTaskEntity.setTaskDesc(pipTaskColumn.get("taskDesc")==null?"":pipTaskColumn.get("taskDesc").toString());
				pipTaskEntity.setImplDate(pipTaskColumn.get("implDate")==null?"":pipTaskColumn.get("implDate").toString());
				pipTaskEntity.setPercentCompletion(pipTaskColumn.get("percentCompletion")==null?"":pipTaskColumn.get("percentCompletion").toString());
				pipTaskEntity.setNextStep(pipTaskColumn.get("nextStep")==null?"":pipTaskColumn.get("nextStep").toString());
				pipTaskEntity.setConfidenceLevel(pipTaskColumn.get("confidenceLevel")==null?"":pipTaskColumn.get("confidenceLevel").toString());
				pipTaskEntity.setCreatedDate(createdDate);
				pipTaskEntity.setUpdatedDate(createdDate);
				pipTasksForPipAction.add(pipTaskEntity);
			}
			pipEntity.setPipTasks(pipTasksForPipAction);
			pipActionRepository.save(pipEntity);

		}
		}
		/// save data in pipactions table//

		//pipFinancials/////
		List<PipFinancials> existingPipFinancials=pipFinancialsRepository.findPipFinancialDataByProject(projectId.toString());
		if(existingPipFinancials.size()==0) {
		for(int k=0;k<pipFinancialsList.size();k++) {
			Map pipFinancialsColumn=pipFinancialsList.get(k);
			PipFinancials pipfinancialEntity=new PipFinancials(); 
			pipfinancialEntity.setProjectId(projectId.toString());
			pipfinancialEntity.setComment(pipFinancialsColumn.get("comment")==null?"":pipFinancialsColumn.get("comment").toString());
			pipfinancialEntity.setComment(pipFinancialsColumn.get("comment")==null?"":pipFinancialsColumn.get("comment").toString());
			pipfinancialEntity.setMappingVariable(pipFinancialsColumn.get("mappingVariable")==null?"":pipFinancialsColumn.get("mappingVariable").toString());
			pipfinancialEntity.setUpdatedDate(createdDate);
			pipfinancialEntity.setMonth(createdDate);
			pipfinancialEntity.setCreatedDate(createdDate);
			pipfinancialEntity.setMonth(startDate.split(" ")[1].substring(0,3));
			pipfinancialEntity.setYear(startDate.split(" ")[2]);
			pipFinancialsRepository.save(pipfinancialEntity);
		}
		}
		//pipfinancials/////
		
		
		////projectMetricsDetail save///
		List<ProjectMetricsDetail> existingMetricesData=projectMetricsDetailRepository.findByprojectId(projectId);
		if(existingMetricesData.size()==0) {
		for(int l=0;l<projectMetricsDetailList.size();l++) {
			Map ProjectMetricsDetailMap=projectMetricsDetailList.get(l);
			ProjectMetricsDetail entity=new  ProjectMetricsDetail(); 
			entity.setProjectId(projectId);
			entity.setMetricsId(ProjectMetricsDetailMap.get("metricsId")==null?null:Integer.parseInt(ProjectMetricsDetailMap.get("metricsId").toString()));
			entity.setComment(ProjectMetricsDetailMap.get("comment")==null?"":ProjectMetricsDetailMap.get("comment").toString());
			entity.setUpdatedOn(new Date(createdDate));
			entity.setCreatedOn(new Date(createdDate));
			entity.setReportedMonth(startDate.split(" ")[1]+" "+startDate.split(" ")[2]);
			projectMetricsDetailRepository.save(entity);
		}
		}
		////projectMetricsDetail save///
		
		////projectGateDetails data save////
		List<ProjectGateDetail> existingGateData=projectGateDetailRepository.findByProjectIdAndReportedMonth(projectId,startDate.split(" ")[1]+" "+startDate.split(" ")[2], null);
		if(existingGateData.size()==0) {
		for(int m=0;m<projectGateDetailsList.size();m++) {
			Map gateColumn=projectGateDetailsList.get(m);
			ProjectGateDetail entity=new ProjectGateDetail();
			entity.setProjectId(projectId);
			entity.setGateId(gateColumn.get("gateId")==null?null:Integer.parseInt(gateColumn.get("gateId").toString()));
			SimpleDateFormat originalFormat = new SimpleDateFormat("ddMMyyyy");
			String[] a=gateColumn.get("initialDate").toString().split(" ")[0].split("-");

			Calendar c = Calendar.getInstance();
			c.set(Integer.parseInt(a[0].toString()), (Integer.parseInt(a[1].toString())) - 1, Integer.parseInt(a[2].toString()), 0, 0);  


			//			Date d=new Date();
//			d.setYear(Integer.parseInt(a[0].toString()));
//			d.setMonth(Integer.parseInt(a[1].toString()));
//			d.setDate(1);
//			Long value=Long.getLong(gateColumn.get("initialDate").toString());
//			new SimpleDateFormat("yyyyMMdd").parse(gateColumn.get("initialDate").toString())
//			Date date = originalFormat.parse(d);
			System.out.println();
			entity.setInitialDate(c.getTime());
			entity.setUpdatedOn(new Date(createdDate));
			entity.setCreatedOn(new Date(createdDate));
			entity.setIsFreezed(gateColumn.get("isFreezed")==null?0:Integer.parseInt(gateColumn.get("gateId").toString()));
			entity.setReportedMonth(startDate.split(" ")[1]+" "+startDate.split(" ")[2]);
			projectGateDetailRepository.save(entity);
		}
		}
		////projectGateDetails data save///
		return "";
	}
	
	
	public String updateProjectWhenItsGPOR(Map project) {
		Map projectMap =(Map) project.get("project");
		String projectRefId=projectMap.get("projectRefId")==null?"":projectMap.get("projectRefId").toString();
		Project existenceData= projectRepository.findByprojectRefId(projectRefId);
		Project projectEntity=new Project();

		if(existenceData!=null) {
			projectEntity=existenceData;
		}
		projectEntity.setProjectRefId(projectMap.get("projectRefId").toString());
		projectEntity.setName(projectMap.get("name")==null?"":projectMap.get("name").toString());
		projectEntity.setDescription(projectMap.get("description")==null?"":projectMap.get("description").toString());
		projectEntity.setCustomerGroupId(projectMap.get("customerGroupId")==null?0:Integer.parseInt(projectMap.get("customerGroupId").toString()));
		projectEntity.setCustomerGroupName(projectMap.get("customerGroupName")==null?"":projectMap.get("customerGroupName").toString());
		projectEntity.setBrandId(projectMap.get("brandId")==null?0:Integer.parseInt(projectMap.get("brandId").toString()));
		projectEntity.setBrandName(projectMap.get("brandName")==null?"":projectMap.get("brandName").toString());
		projectEntity.setCustomerId(projectMap.get("customerId")==null?0:Integer.parseInt(projectMap.get("customerId").toString()));
		projectEntity.setCustomerName(projectMap.get("customerName")==null?"":projectMap.get("customerName").toString());
		projectEntity.setUnitId(projectMap.get("unitId")==null?0:Integer.parseInt(projectMap.get("unitId").toString()));
		projectEntity.setUnitName(projectMap.get("unitName")==null?"":projectMap.get("unitName").toString());
		projectEntity.setUnitDescription(projectMap.get("unitDescription")==null?"":projectMap.get("unitDescription").toString());
		projectEntity.setSopMonth(projectMap.get("sopMonth")==null?"":projectMap.get("sopMonth").toString());
		projectEntity.setSopYear(projectMap.get("sopYear")==null?0:Integer.parseInt(projectMap.get("sopYear").toString()));
		projectEntity.setProjectLife(projectMap.get("projectLife")==null?"":projectMap.get("projectLife").toString());
		projectEntity.setProjectLife(projectMap.get("projectLife")==null?"":projectMap.get("projectLife").toString());
		projectEntity.setRecordStatusID(1);
		projectEntity.setAwardDate(projectMap.get("awardDate")==null?"":projectMap.get("awardDate").toString());
		projectEntity.setProjectStatusId(projectMap.get("projectStatusId")==null?0:Integer.parseInt(projectMap.get("projectStatusId").toString()));
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());
		projectEntity.setCreatedDate(createdDate);
		projectEntity.setUpdatedDate(createdDate);
		projectEntity.setCurrenyId(projectMap.get("currenyId")==null?0:Integer.parseInt(projectMap.get("currenyId").toString()));
		projectEntity.setCurrencyName(projectMap.get("currencyName")==null?"":projectMap.get("currencyName").toString());
		Project saveProjectEntity=projectRepository.save(projectEntity);
		
		System.out.println(project);
		return "";
	}
		}


