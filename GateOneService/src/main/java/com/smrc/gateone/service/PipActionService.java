package com.smrc.gateone.service;

import java.util.List;

import com.smrc.gateone.model.MappingVariablesForPIPAction;
import com.smrc.gateone.model.PipAction;
import com.smrc.gateone.model.PipSaveJson;

public interface PipActionService {

	public List<PipAction> getPipActionData(Integer projectid);
	
	public List<MappingVariablesForPIPAction> pipFinantialData(Integer projectid, List<PipAction> list);
	
	public String savePipActionData(PipSaveJson parameter);
	
}
