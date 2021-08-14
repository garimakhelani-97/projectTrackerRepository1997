package com.smrc.gpor.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.model.AccountMonthDetail_PIP;
import com.smrc.gpor.model.MappingVariablesForPIPAction;
import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.PipSaveJson;
import com.smrc.gpor.model.PipTask;
import com.smrc.gpor.model.Project;
import com.smrc.gpor.model.ProjectApprover;
import com.smrc.gpor.model.SavePipData;
import com.smrc.gpor.repository.AccountMonthDetail_PIPRepository;
import com.smrc.gpor.repository.PipActionRepository;
import com.smrc.gpor.repository.PipTaskRepository;
import com.smrc.gpor.repository.ProjectApproverRepository;
import com.smrc.gpor.repository.ProjectRepository;

@Service
public class PipActionServiceImpl implements PipActionService{
	@Autowired
	private PipActionRepository pipActionRepository;
	@Autowired
	private PipTaskRepository pipTaskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private AccountMonthDetail_PIPRepository accountMonthDetail_PIPRepository;
	@Autowired 
    ProjectApproverRepository projectApproverRepository;
	
	public List<PipAction> getPipActionData(Integer projectid, String month,String year,String pmonth,String pyear) {

		List<PipAction> currentMonthData=pipActionRepository.findPipActionDataByProject(projectid, month,year);
		List<PipAction> monthData=new ArrayList<PipAction>(); 
		monthData=currentMonthData;
		if(!(monthData.size()>0)) {
			List<ProjectApprover> previousMonthApproveData= projectApproverRepository.lastMonthApprovedStatus(projectid,pmonth,pyear);
			List<PipAction> previousMonthData=new ArrayList<PipAction>();
			if(previousMonthApproveData.size()>0) {
				previousMonthData=pipActionRepository.findPipActionDataByProject(projectid, pmonth,pyear);
			}
			monthData= previousMonthData;
		}
		return monthData;
		
	}
	
	public List<MappingVariablesForPIPAction> pipFinantialData(Integer projectid, String month,String year,String pmonth,String pyear,List<PipAction> pipdata){
		List<Map> returnMap=new ArrayList();
		List<Map>findMV= pipActionRepository.getMappingVariablesAgainstAccountCode(month,year,projectid);
		Double ebit=0.0,irr=0.0,roce=0.0;
		for(int x=0;x<pipdata.size();x++) {
			String ebitImpact=((PipAction)pipdata.get(x)).getEbitImpact().isEmpty()?"0": ((PipAction)pipdata.get(x)).getEbitImpact();
			String irrImpact=((PipAction)pipdata.get(x)).getIrrImpact().isEmpty()?"0": ((PipAction)pipdata.get(x)).getIrrImpact();
			String roceImpact=((PipAction)pipdata.get(x)).getRoceImpact().isEmpty()?"0": ((PipAction)pipdata.get(x)).getRoceImpact();
			System.out.println(ebitImpact+irrImpact+roceImpact);
			ebit=ebit+Double.parseDouble(ebitImpact);
			irr=irr+Double.parseDouble(irrImpact);
			roce=roce+Double.parseDouble(roceImpact);
			
		}
		List<MappingVariablesForPIPAction> returnList=new ArrayList<MappingVariablesForPIPAction>();
		
		for(int y=0;y<findMV.size();y++) {
			MappingVariablesForPIPAction Entry =new MappingVariablesForPIPAction();
			if(((Map)findMV.get(y)).get("shortHeadName").equals("ROCE")) {
				Double currentValueROCE=((Map)findMV.get(y)).get("currentvalue")==null||((Map)findMV.get(y)).get("currentvalue").toString().isEmpty()?0.0: Double.parseDouble(((Map)findMV.get(y)).get("currentvalue").toString().replace("%", ""));
				Double eopROCE=roce+currentValueROCE;
				Double gate1commitvalueROCE=((Map)findMV.get(y)).get("gate1commitvalue")==null||((Map)findMV.get(y)).get("gate1commitvalue").toString().isEmpty()?0.0:Double.parseDouble(((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
//				((Map)findMV.get(y)).get("gate1commitvalue").toString().isEmpty()
				Double endofprojectVSGate1=eopROCE-gate1commitvalueROCE;
				
				Entry.setCurrentvalue(((Map)findMV.get(y)).get("currentvalue")==null||((Map)findMV.get(y)).get("currentvalue").toString().isEmpty()?"0.0": ((Map)findMV.get(y)).get("currentvalue").toString());
				Entry.setShortHeadName(((Map)findMV.get(y)).get("shortHeadName")==null||((Map)findMV.get(y)).get("shortHeadName").toString().isEmpty()?"":((Map)findMV.get(y)).get("shortHeadName").toString());
				Entry.setMappingVariable(((Map)findMV.get(y)).get("mappingVariable")==null||((Map)findMV.get(y)).get("mappingVariable").toString().isEmpty()?"":((Map)findMV.get(y)).get("mappingVariable").toString());
				Entry.setGate1commitvalue(((Map)findMV.get(y)).get("gate1commitvalue")==null||((Map)findMV.get(y)).get("gate1commitvalue").toString().isEmpty()?"0.0":((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
				Entry.setComment(((Map)findMV.get(y)).get("comment")==null||((Map)findMV.get(y)).get("comment").toString().isEmpty()?"":((Map)findMV.get(y)).get("comment").toString());
				Entry.setId(((Map)findMV.get(y)).get("id")==null?0:Integer.parseInt(((Map)findMV.get(y)).get("id").toString()));
				Entry.setEndofproject(eopROCE.toString());
				Entry.setPIP(roce.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}
			if(((Map)findMV.get(y)).get("shortHeadName").equals("IRR")) {
				Double currentValueIRR=((Map)findMV.get(y)).get("currentvalue")==null||
						((Map)findMV.get(y)).get("currentvalue").toString().isEmpty()?0.0: Double.parseDouble(((Map)findMV.get(y)).get("currentvalue").toString().replace("%", ""));
				Double eopIRR=irr+currentValueIRR;
				Double gate1commitvalueIRR=((Map)findMV.get(y)).get("gate1commitvalue")==null||
						((Map)findMV.get(y)).get("gate1commitvalue").toString().isEmpty()?0.0:Double.parseDouble(((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
				Double endofprojectVSGate1=eopIRR-gate1commitvalueIRR;

				Entry.setCurrentvalue(((Map)findMV.get(y)).get("currentvalue")==null?"0.0":((Map)findMV.get(y)).get("currentvalue").toString());
				Entry.setShortHeadName(((Map)findMV.get(y)).get("shortHeadName")==null?"":((Map)findMV.get(y)).get("shortHeadName").toString());
				Entry.setMappingVariable(((Map)findMV.get(y)).get("mappingVariable")==null?"":((Map)findMV.get(y)).get("mappingVariable").toString());
				Entry.setGate1commitvalue(((Map)findMV.get(y)).get("gate1commitvalue")==null?"0.0":((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
				Entry.setComment(((Map)findMV.get(y)).get("comment")==null?"":((Map)findMV.get(y)).get("comment").toString());
				Entry.setId(((Map)findMV.get(y)).get("id")==null?0:Integer.parseInt(((Map)findMV.get(y)).get("id").toString()));
				Entry.setEndofproject(eopIRR.toString());
				Entry.setPIP(irr.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}

			if(((Map)findMV.get(y)).get("shortHeadName").equals("Total EBIT % of Sales")) {

				Double currentValueEBIT=((Map)findMV.get(y)).get("currentvalue")==null||
						((Map)findMV.get(y)).get("currentvalue").toString().isEmpty()?0.0:Double.parseDouble(((Map)findMV.get(y)).get("currentvalue").toString().replace("%", ""));
				Double eopEBIT=ebit+currentValueEBIT;
				Double gate1commitvalueEBIT=((Map)findMV.get(y)).get("gate1commitvalue")==null||
						((Map)findMV.get(y)).get("gate1commitvalue").toString().isEmpty()?0.0:Double.parseDouble(((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
				Double endofprojectVSGate1=eopEBIT-gate1commitvalueEBIT;

				Entry.setCurrentvalue(((Map)findMV.get(y)).get("currentvalue")==null?"0.0":((Map)findMV.get(y)).get("currentvalue").toString());
				Entry.setShortHeadName(((Map)findMV.get(y)).get("shortHeadName")==null?"":((Map)findMV.get(y)).get("shortHeadName").toString());
				Entry.setMappingVariable(((Map)findMV.get(y)).get("mappingVariable")==null?"":((Map)findMV.get(y)).get("mappingVariable").toString());
				Entry.setGate1commitvalue(((Map)findMV.get(y)).get("gate1commitvalue")==null?"0.0":((Map)findMV.get(y)).get("gate1commitvalue").toString().replace("%", ""));
				Entry.setComment(((Map)findMV.get(y)).get("comment")==null?"":((Map)findMV.get(y)).get("comment").toString());
				Entry.setId(((Map)findMV.get(y)).get("id")==null?0:Integer.parseInt(((Map)findMV.get(y)).get("id").toString()));
				Entry.setEndofproject(eopEBIT.toString());
				Entry.setPIP(ebit.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}
		}
		
		
		
		return returnList;
	}
	

	@Transactional
	public String savePipActionData(PipSaveJson parameter) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String createdDate = formatter.format(new Date());
		Integer projectId =Integer.parseInt(parameter.getProjectId());//Integer.parseInt(parameter.get("projectId").toString());// get project id
		String month =parameter.getMonth();// parameter.get("month") == null ? "" : parameter.get("month").toString();
		String year =parameter.getYear() ;//parameter.get("year") == null ? "" : parameter.get("year").toString();
		String createdBy =parameter.getCreatedBy() ;//parameter.get("createdBy") == null ? "0": parameter.get("createdBy").toString();
		if (month == "" || year == "" ||parameter.getMonth() == null || parameter.getYear() == null) {
			return "Month and year not define properly ";
		}
		
		
		//delete data against projectid,month and year
		List<PipAction> deleteList= pipActionRepository.findPipActionDataByProject(projectId,month,year);
		deleteList.forEach((n) -> pipActionRepository.delete(n) ); 

		System.out.println("project ID " + projectId);
		List<PipAction> pipActions = (List<PipAction>) parameter.getPipActions();//("pipActions");// get List of pipActions

		for (int i = 0; i < pipActions.size(); i++) {

			PipAction targetPipAction =  pipActions.get(i);
			PipAction actionEntity = new PipAction();
//			if (targetPipAction.get("id") != null && targetPipAction.get("id") != "") {
//				Optional<PipAction> Entity = pipActionRepository
//						.findById(Integer.parseInt(targetPipAction.get("id").toString()));
//				if (Entity.isPresent()) {
//					actionEntity = Entity.get();
//				}
//			}

			String pipaction = targetPipAction.getPipaction() == null ? ""
					: targetPipAction.getPipaction();
			String lcVeh = targetPipAction.getLcVeh() == null ? "" : targetPipAction.getLcVeh();
			String conflevel = targetPipAction.getConflevel()== null ? ""
					: targetPipAction.getConflevel();
			String ebitImpact = targetPipAction.getEbitImpact() == null ? ""
					: targetPipAction.getEbitImpact();
			String irrImpact = targetPipAction.getIrrImpact()== null ? ""
					: targetPipAction.getIrrImpact();
			String roceImpact = targetPipAction.getRoceImpact() == null ? ""
					: targetPipAction.getRoceImpact();
			String implDate = targetPipAction.getImplDate() == null ? "" : targetPipAction.getImplDate();
			String comment = targetPipAction.getComment() == null ? "" : targetPipAction.getComment();
		

			Set<PipTask> pipTasksForPipAction = new HashSet<PipTask>();
			Set<PipTask> pipTasks = targetPipAction.getPipTasks() == null ? new HashSet()
					: (Set<PipTask>) targetPipAction.getPipTasks();// get List of pipTasks against pipAction
//			for (int j = 0; j < pipTasks.size(); j++) {
				 for (PipTask entry:pipTasks) {
				PipTask targetPipTask = entry;

				String taskDesc = targetPipTask.getTaskDesc() == null ? "" : targetPipTask.getTaskDesc();
				String implDateTask = targetPipTask.getImplDate() == null ? ""
						: targetPipTask.getImplDate();
				String percentCompletion = targetPipTask.getPercentCompletion() == null ? ""
						: targetPipTask.getPercentCompletion();
				String nextStep = targetPipTask.getNextStep() == null ? "" : targetPipTask.getNextStep();
				String confidenceLevel = targetPipTask.getConfidenceLevel() == null ? ""
						: targetPipTask.getConfidenceLevel();
//				String createdByTask = targetPipTask.get("createdBy") == null ? "0"
//						: targetPipTask.get("createdBy").toString();
				String ipAddress = targetPipTask.getIpAddress() == null ? "0.0.0.0"
						: targetPipTask.getIpAddress();

				PipTask taskEntity = new PipTask();

//				if (targetPipTask.get("id") != null && targetPipTask.get("id") != "") {
//
//					Optional<PipTask> existingTaskEntity = pipTaskRepository
//							.findById(Integer.parseInt(targetPipTask.get("id").toString()));
//					if (existingTaskEntity.isPresent()) {
//						taskEntity = existingTaskEntity.get();
//					}
//				}
				taskEntity.setTaskDesc(taskDesc);
				taskEntity.setImplDate(implDateTask);
				taskEntity.setPercentCompletion(percentCompletion);
				taskEntity.setNextStep(nextStep);
				taskEntity.setConfidenceLevel(confidenceLevel);
				taskEntity.setCreatedBy(Integer.parseInt(createdBy));
				taskEntity.setCreatedDate(createdDate);
				taskEntity.setIpAddress(ipAddress);
				pipTasksForPipAction.add(taskEntity);
			}

			actionEntity.setMonth(month);
			actionEntity.setYear(year);
			actionEntity.setPipaction(pipaction);
			actionEntity.setLcVeh(lcVeh);
			actionEntity.setConflevel(conflevel);
			actionEntity.setEbitImpact(ebitImpact);
			actionEntity.setIrrImpact(irrImpact);
			actionEntity.setRoceImpact(roceImpact);
			actionEntity.setImplDate(implDate);
			actionEntity.setComment(comment);

			actionEntity.setPipTasks(pipTasksForPipAction);
//			if (actionEntity.getId() != null) {
				actionEntity.setUpdatedBy(Integer.parseInt(createdBy));
				actionEntity.setUpdatedDate(createdDate);
//			} else {
				actionEntity.setCreatedBy(Integer.parseInt(createdBy));
				actionEntity.setCreatedDate(createdDate);
//			}
			Optional<Project> project = projectRepository.findById(projectId);
			if(project.isPresent()){
				actionEntity.setProject(project.get());
//			}
			
			}
			pipActionRepository.save(actionEntity);// save pipAction and pipTask by cascade=CascadeType.ALL property
		}

		List<MappingVariablesForPIPAction> accountMonthDetail=parameter.getAccountMonthDetail()==null?new ArrayList<MappingVariablesForPIPAction>(): (List<MappingVariablesForPIPAction>) parameter.getAccountMonthDetail();//("accountMonthDetail");
		for(int a=0;a<accountMonthDetail.size();a++) {
			MappingVariablesForPIPAction targetElement=(MappingVariablesForPIPAction)accountMonthDetail.get(a);
			
			Optional<AccountMonthDetail_PIP> entity1=accountMonthDetail_PIPRepository.findById(Integer.parseInt(targetElement.getId().toString()));
			if(entity1.isPresent()) {
				AccountMonthDetail_PIP accountMonthDetail_PIP=entity1.get();
				accountMonthDetail_PIP.setComment(targetElement.getComment().toString());
				accountMonthDetail_PIP.setUpdatedDate(createdDate);
//				accountMonthDetail_PIP.setUpdatedBy(createdBy);
				
				accountMonthDetail_PIPRepository.save(accountMonthDetail_PIP);
			}
			
		}
		
		return "success";
	}

}
