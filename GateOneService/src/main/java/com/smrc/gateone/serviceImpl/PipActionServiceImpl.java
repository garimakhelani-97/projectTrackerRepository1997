package com.smrc.gateone.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gateone.model.MappingVariablesForPIPAction;
import com.smrc.gateone.model.PipAction;
import com.smrc.gateone.model.PipFinancials;
import com.smrc.gateone.model.PipSaveJson;
import com.smrc.gateone.model.PipTask;
import com.smrc.gateone.model.Project;
import com.smrc.gateone.repository.PipActionRepository;
import com.smrc.gateone.repository.PipFinancialsRepository;
import com.smrc.gateone.repository.ProjectRepository;
import com.smrc.gateone.service.PipActionService;


@Service
public class PipActionServiceImpl implements PipActionService {
	
	@Autowired
	private PipActionRepository pipActionRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private PipFinancialsRepository pipFinancialsRepository;
	
	public List<PipAction> getPipActionData(Integer projectid) {

		List<PipAction> currentMonthData = pipActionRepository.findPipActionDataByProject(projectid);
		return currentMonthData;

	}

	public List<MappingVariablesForPIPAction> pipFinantialData(Integer projectid, List<PipAction> pipdata) {
		List<Map> findMV = pipActionRepository.getMappingVariablesAgainstAccountCode(projectid);
		
		List<PipFinancials> pipFinancials = pipFinancialsRepository.findPipFinancialDataByProject(projectid);
		
		Map<String, String> pipFinancialsMap = prepareFinancialsMap(pipFinancials); 
		Double ebit = 0.0, irr = 0.0, roce = 0.0;
		for (int x = 0; x < pipdata.size(); x++) {
			String ebitImpact = ((PipAction) pipdata.get(x)).getEbitImpact().isEmpty() ? "0"
					: ((PipAction) pipdata.get(x)).getEbitImpact();
			String irrImpact = ((PipAction) pipdata.get(x)).getIrrImpact().isEmpty() ? "0"
					: ((PipAction) pipdata.get(x)).getIrrImpact();
			String roceImpact = ((PipAction) pipdata.get(x)).getRoceImpact().isEmpty() ? "0"
					: ((PipAction) pipdata.get(x)).getRoceImpact();
			
			System.out.println(ebitImpact + irrImpact + roceImpact);
			
			ebit = ebit + Double.parseDouble(ebitImpact);
			irr = irr + Double.parseDouble(irrImpact);
			roce = roce + Double.parseDouble(roceImpact);

		}
		List<MappingVariablesForPIPAction> returnList = new ArrayList<MappingVariablesForPIPAction>();

		for (int y = 0; y < findMV.size(); y++) {
			MappingVariablesForPIPAction Entry = new MappingVariablesForPIPAction();
			//((Map) findMV.get(y)).get("misAccountCode").equals("ROCE")
			Map map = (Map)findMV.get(y);
			String misAccountCode = (String) map.get("misAccountCode");
			if (StringUtils.equals("8008", misAccountCode)) {
				Double securedProfitability = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double eopROCE = roce + securedProfitability;
				Double gate1commitvalueROCE = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double endofprojectVSGate1 = eopROCE - gate1commitvalueROCE;

				Entry.setSecuredProfitability(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString());
				Entry.setShortHeadName(map.get("shortHeadName") == null ? ""
						: map.get("shortHeadName").toString());
				Entry.setMappingVariable(map.get("mappingVariable") == null ? ""
						: map.get("mappingVariable").toString());
				Entry.setCommitGate(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString().replace("%", ""));
				
				Entry.setComment(pipFinancialsMap.get(map.get("mappingVariable").toString()));
				
				Entry.setId(map.get("id") == null ? 0
						: Integer.parseInt(map.get("id").toString()));
				Entry.setEndofproject(eopROCE.toString());
				Entry.setPIP(roce.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}
			//((Map) findMV.get(y)).get("misAccountCode").equals("IRR")
			if (StringUtils.equals("8009", misAccountCode)) {
				Double currentValueIRR = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double eopIRR = irr + currentValueIRR;
				Double gate1commitvalueIRR = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double endofprojectVSGate1 = eopIRR - gate1commitvalueIRR;

				Entry.setSecuredProfitability(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString());
				Entry.setShortHeadName(map.get("shortHeadName") == null ? ""
						: map.get("shortHeadName").toString());
				Entry.setMappingVariable(map.get("mappingVariable") == null ? ""
						: map.get("mappingVariable").toString());
				Entry.setCommitGate(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString().replace("%", ""));
				
				Entry.setComment(pipFinancialsMap.get(map.get("mappingVariable").toString()));
				
				Entry.setId(map.get("id") == null ? 0
						: Integer.parseInt(map.get("id").toString()));
				Entry.setEndofproject(eopIRR.toString());
				Entry.setPIP(irr.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}
			// ((Map) findMV.get(y)).get("misAccountCode").equals("Total EBIT % of Sales")
			if (StringUtils.equals("8012", misAccountCode)) {

				Double currentValueEBIT = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double eopEBIT = ebit + currentValueEBIT;
				Double gate1commitvalueEBIT = map.get("securedProfitability") == null ? 0.0
						: Double.parseDouble(map.get("securedProfitability").toString().replace("%", ""));
				Double endofprojectVSGate1 = eopEBIT - gate1commitvalueEBIT;

				Entry.setSecuredProfitability(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString());
				Entry.setShortHeadName(map.get("shortHeadName") == null ? ""
						: map.get("shortHeadName").toString());
				Entry.setMappingVariable(map.get("mappingVariable") == null ? ""
						: map.get("mappingVariable").toString());
				Entry.setCommitGate(map.get("securedProfitability") == null ? "0.0"
						: map.get("securedProfitability").toString().replace("%", ""));
				
				Entry.setComment(pipFinancialsMap.get(map.get("mappingVariable").toString()));
				
				Entry.setId(map.get("id") == null ? 0
						: Integer.parseInt(map.get("id").toString()));
				Entry.setEndofproject(eopEBIT.toString());
				Entry.setPIP(ebit.toString());
				Entry.setEndofprojectVSGate1(endofprojectVSGate1.toString());
				returnList.add(Entry);
			}
		}

		return returnList;
	}

	private Map<String, String> prepareFinancialsMap(List<PipFinancials> pipFinancials) {
		Map<String, String> map = new HashMap<String, String>();
		for (PipFinancials pipFinancial : pipFinancials) {
			map.put(pipFinancial.getMappingVariable(), pipFinancial.getComment() != null ? pipFinancial.getComment() : "");
		}
		return map;
	}

	@Transactional
	public String savePipActionData(PipSaveJson parameter) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
		formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String createdDate = formatter.format(new Date());
		Integer projectId = Integer.parseInt(parameter.getProjectId());

		String createdBy = parameter.getCreatedBy();
		
		// delete data against projectid
		
		List<PipAction> deleteList = pipActionRepository.findPipActionDataByProject(projectId);
		deleteList.forEach((n) -> pipActionRepository.delete(n));

		List<PipAction> pipActions = (List<PipAction>) parameter.getPipActions();
		
		// pipActions

		for (int i = 0; i < pipActions.size(); i++) {

			PipAction targetPipAction = pipActions.get(i);
			PipAction actionEntity = new PipAction();

			String pipaction = targetPipAction.getPipaction() == null ? "" : targetPipAction.getPipaction();
			String lcVeh = targetPipAction.getLcVeh() == null ? "" : targetPipAction.getLcVeh();
			String conflevel = targetPipAction.getConflevel() == null ? "" : targetPipAction.getConflevel();
			String ebitImpact = targetPipAction.getEbitImpact() == null ? "" : targetPipAction.getEbitImpact();
			String irrImpact = targetPipAction.getIrrImpact() == null ? "" : targetPipAction.getIrrImpact();
			String roceImpact = targetPipAction.getRoceImpact() == null ? "" : targetPipAction.getRoceImpact();
			String implDate = targetPipAction.getImplDate() == null ? "" : targetPipAction.getImplDate();
			String comment = targetPipAction.getComment() == null ? "" : targetPipAction.getComment();

			Set<PipTask> pipTasksForPipAction = new HashSet<PipTask>();
			Set<PipTask> pipTasks = targetPipAction.getPipTasks() == null ? new HashSet()
					: (Set<PipTask>) targetPipAction.getPipTasks();// get List of pipTasks against pipAction
			for (PipTask entry : pipTasks) {
				PipTask targetPipTask = entry;

				String taskDesc = targetPipTask.getTaskDesc() == null ? "" : targetPipTask.getTaskDesc();
				String implDateTask = targetPipTask.getImplDate() == null ? "" : targetPipTask.getImplDate();
				String percentCompletion = targetPipTask.getPercentCompletion() == null ? ""
						: targetPipTask.getPercentCompletion();
				String nextStep = targetPipTask.getNextStep() == null ? "" : targetPipTask.getNextStep();
				String confidenceLevel = targetPipTask.getConfidenceLevel() == null ? ""
						: targetPipTask.getConfidenceLevel();
				String ipAddress = targetPipTask.getIpAddress() == null ? "0.0.0.0" : targetPipTask.getIpAddress();

				PipTask taskEntity = new PipTask();

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

			actionEntity.setPipaction(pipaction);
			actionEntity.setLcVeh(lcVeh);
			actionEntity.setConflevel(conflevel);
			actionEntity.setEbitImpact(ebitImpact);
			actionEntity.setIrrImpact(irrImpact);
			actionEntity.setRoceImpact(roceImpact);
			actionEntity.setImplDate(implDate);
			actionEntity.setComment(comment);

			actionEntity.setPipTasks(pipTasksForPipAction);
			actionEntity.setUpdatedBy(Integer.parseInt(createdBy));
			actionEntity.setUpdatedDate(createdDate);
			actionEntity.setCreatedBy(Integer.parseInt(createdBy));
			actionEntity.setCreatedDate(createdDate);
			Optional<Project> project = projectRepository.findById(projectId);
			
			if (project.isPresent()) {
				actionEntity.setProject(project.get());
			}
			
			// save pipAction and pipTask by cascade=CascadeType.ALL property
			pipActionRepository.save(actionEntity);
		}

		List<PipFinancials> pipFinancials=parameter.getAccountMonthDetail()==null?new ArrayList<PipFinancials>(): (List<PipFinancials>) parameter.getAccountMonthDetail();//("accountMonthDetail");
		for(int a=0;a<pipFinancials.size();a++) {
			PipFinancials pipFinancial=(PipFinancials)pipFinancials.get(a);
			
			List<PipFinancials> financials=pipFinancialsRepository.findAllByProjectIdAndMappingVariable(projectId, pipFinancial.getMappingVariable());
			if(null != financials && financials.size()>0) {
				PipFinancials financials2=financials.get(0);
				financials2.setComment(pipFinancial.getComment()==null?"": pipFinancial.getComment().toString());
				financials2.setUpdatedDate(createdDate);
				financials2.setUpdatedBy(Integer.parseInt(createdBy));
				pipFinancialsRepository.save(financials2);
			}
			else {
				PipFinancials financials2=new PipFinancials();
				financials2.setComment(pipFinancial.getComment()==null?"": pipFinancial.getComment().toString());
				financials2.setUpdatedDate(createdDate);
				financials2.setMappingVariable(pipFinancial.getMappingVariable());
				financials2.setProjectId(projectId);
				financials2.setCreatedBy(Integer.parseInt(createdBy));
				financials2.setCreatedDate(createdDate);
				pipFinancialsRepository.save(financials2);
			}
			
		}
		return "success";
	}

}
