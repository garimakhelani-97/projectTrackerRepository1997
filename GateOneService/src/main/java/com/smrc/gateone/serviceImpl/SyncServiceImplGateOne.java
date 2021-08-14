package com.smrc.gateone.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gateone.model.Gate1ProjectProcessAssociation;
import com.smrc.gateone.model.Project;
import com.smrc.gateone.repository.Gate1ProjectProcessAssociationRepository;
import com.smrc.gateone.repository.ProjectRepository;

@Service
public class SyncServiceImplGateOne {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private Gate1ProjectProcessAssociationRepository gate1ProjectProcessAssociationRepository;
	
	@Autowired
	private  SyncGPORServiceImpl syncGPORServiceImpl;

	public String addProject(Map projectParameter, String projectId) {
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
				: ((Map) projectParameter.get("plant")).get("id").toString();
		String unitname = ((Map) projectParameter.get("plant")).get("name") == null ? ""
				: ((Map) projectParameter.get("plant")).get("name").toString();
		String unitDescription = ((Map) projectParameter.get("plant")).get("description") == null ? ""
				: ((Map) projectParameter.get("plant")).get("description").toString();

		String projectStatusId = ((Map) projectParameter.get("projectStatus")).get("id") == null ? ""
				: ((Map) projectParameter.get("projectStatus")).get("id").toString();
		Project entity = new Project();
		Project existingEntity= projectRepository.findByprojectRefId(projectId);
		if(existingEntity!=null) {
			entity=existingEntity;
		}
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
		String projectLife = projectParameter.get("projectLife").toString();

		entity.setProjectLife(projectLife);
		entity.setSopMonth(sopDateList[1]);
		entity.setSopYear(Integer.parseInt(sopDateList[2]));
		entity.setProjectStatusId(Integer.parseInt(projectStatusId));
		Integer currencyId = ((Map) projectParameter.get("currency")).get("id") == null ? 0
				: Integer.parseInt(((Map) projectParameter.get("currency")).get("id").toString());
		String currencyName = ((Map) projectParameter.get("currency")).get("name") == null ? ""
				: ((Map) projectParameter.get("currency")).get("name").toString();
		entity.setCurrenyId(currencyId);
		entity.setCurrencyName(currencyName);
		formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		String createdDate = formatter.format(new Date());
		entity.setCreatedDate(createdDate);
//		entity.setUpdatedDate(createdDate);
		Project saveEntity = projectRepository.save(entity);
		Gate1ProjectProcessAssociation GPORProcessExistence=gate1ProjectProcessAssociationRepository.findGPORProcessExistence(entity.getId());
		if(GPORProcessExistence!=null) {
			syncGPORServiceImpl.updateProjectWhenItsGPOR(saveEntity);
		}
		System.out.println(saveEntity);
		return "";
	}

}
