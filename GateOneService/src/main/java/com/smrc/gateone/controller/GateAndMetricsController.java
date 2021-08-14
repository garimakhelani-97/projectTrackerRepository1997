package com.smrc.gateone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.dto.GateAndMetricDetailsDTO;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.serviceImpl.GateAndMetricServiceImpl;

@RestController
@RequestMapping("/gateone")

public class GateAndMetricsController {
   @Autowired
   private GateAndMetricServiceImpl gateAndMetricServiceImpl;
	
	
	@GetMapping("/getGateAndMetricsDetails")
	public Map<String,Object> getGateAndMetricDetails(@RequestParam String projectId) {
		Map<String,Object> resMap=new HashMap();
		Integer parsedProjectId = Integer.parseInt(projectId); 
		resMap.put("metricsData", gateAndMetricServiceImpl.getMetricDetails(parsedProjectId));
		resMap.put("gateData", gateAndMetricServiceImpl.getGateDetails(parsedProjectId));
	    PreviewsScreen FreezedStatus=gateAndMetricServiceImpl.getPreviewScreen(parsedProjectId);
	    resMap.put("pipIsFreezed",FreezedStatus==null?0: FreezedStatus.getIsFreezed());
		return resMap;
	}
	
	//Save Gate and Metric details
	@PostMapping("/saveGateAndMetricDetails")
	public void saveGateAndMetricsDetails(@RequestBody GateAndMetricDetailsDTO gateAndMetricDetailsDTO) {
		gateAndMetricServiceImpl.saveGateAndMetricsDetails(gateAndMetricDetailsDTO);
	}
}
