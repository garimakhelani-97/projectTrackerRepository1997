package com.smrc.gpor.service;

import java.util.List;

import com.smrc.gpor.model.MappingVariablesForPIPAction;
import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.PipSaveJson;

public interface PipActionService {

	public List<PipAction> getPipActionData(Integer projectid,String month,String year,String pmonth,String pyear);
	
	public List<MappingVariablesForPIPAction> pipFinantialData(Integer projectid,String month,String year,String pmonth,String pyear,List<PipAction> list);
	
	public String savePipActionData(PipSaveJson parameter);
	
}
