package com.smrc.gpor.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.ProjectGateDetail;
import com.smrc.gpor.repository.GateRepository;
import com.smrc.gpor.repository.ProjectGateDetailRepository;

@Service
public class PhasesDataServiceImpl  {
	@Autowired
	private ProjectGateDetailRepository projectGateDetailRepository;
	@Autowired
	private GateRepository gateRepository;

	
	public String savePhasesdata(Map parameters) throws ParseException {
		List<Map> gateData=(List<Map>) parameters.get("project_gate_detail");
		
		for(int x=0;x<gateData.size();x++) {
			Map targetGate=gateData.get(x);
			Integer isFreezed=targetGate.get("isFreezed")==null? 0: Integer.parseInt(targetGate.get("isFreezed").toString());
			Integer gateid= targetGate.get("gateId")==null?0: Integer.parseInt(targetGate.get("gateId").toString());
			
			Integer pLRR= targetGate.get("percentageLRR")==null? null :Integer.parseInt(targetGate.get("percentageLRR").toString());
			String initialDateString = targetGate.get("initialDate")==null? "":targetGate.get("initialDate").toString();
			// Date initialDate=(Date) targetGate.get("initialDate");
			 Date initialDate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(initialDateString);
			 System.out.println(initialDate);
			 Date effectiveDate = null;
			 String effectiveDateString =targetGate.get("effectiveDate")==null? "": targetGate.get("effectiveDate").toString();
			if(targetGate.get("effectiveDate")!=null) {
			 effectiveDate=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(effectiveDateString);
			}
			 String reportedMonthString= targetGate.get("reportedMonth")==null? "":targetGate.get("reportedMonth").toString();
			 //Date reportedMonth=new SimpleDateFormat("dd/MM/yyyy").parse(reportedMonthString);
			 
//			
			
//			SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy");
//			formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//			String createdDate = formatter.format(new Date());
			
//			SimpleDateFormat sopDateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//			String initialDate = sopDateFormatter.format( targetGate.get("initialDate"));
//			 Date Initialdate1=new SimpleDateFormat("dd MMMM yyyy").parse(initialDate);
			
			Integer projectId= targetGate.get("projectId")==null? 0:Integer.parseInt(targetGate.get("projectId").toString());
			
			Integer id=  targetGate.get("pk")==null? 0 :Integer.parseInt(targetGate.get("pk").toString());
			ProjectGateDetail entity=new ProjectGateDetail();
//			Optional<ProjectGateDetail> entry=projectGateDetailRepository.findById(id);
//			if(entry.isPresent()) {
//			entity=entry.get();
//			
//			};
			ProjectGateDetail entry=projectGateDetailRepository.findByProjectIdGateIdAndReportedMonth(projectId,gateid,reportedMonthString);
			if(entry!=null) {
				entity=entry;
			}
			entity.setGateId(gateid);
			entity.setProjectId(projectId);
			entity.setInitialDate(initialDate);
			entity.setReportedMonth(reportedMonthString);
			entity.setEffectiveDate(effectiveDate);
			entity.setPercentageLRR(pLRR);
			entity.setIsFreezed(isFreezed);
			projectGateDetailRepository.save(entity);
		}
		return "";
	}

	
	public List<ProjectGateDetail> getGateData(Integer projectid,String month) {

		return gateRepository.getGateDetails(projectid,month);
	}
}
