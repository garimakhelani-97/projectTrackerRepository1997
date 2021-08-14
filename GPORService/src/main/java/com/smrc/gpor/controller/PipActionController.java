package com.smrc.gpor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.PipSaveJson;
import com.smrc.gpor.model.PreviewsScreen;
import com.smrc.gpor.model.SavePipData;
import com.smrc.gpor.response.ObjectResponse;
import com.smrc.gpor.service.PipActionService;
import com.smrc.gpor.service.PipActionServiceImpl;
import com.smrc.gpor.serviceImpl.OnePagerServiceImpl;

@RestController
public class PipActionController {

	@Value("${pipActionDisable}")
	private String pipIsFreezedStatus;
	@Autowired
	private PipActionService pipActionService;
	@Autowired
	private OnePagerServiceImpl onePagerServiceImpl;

	@GetMapping("/pip/getpipactiondata/{projectid}/{month}/{year}/{pmonth}/{pyear}")
	public ResponseEntity<ObjectResponse<Object>> getPipActionData(@PathVariable Integer projectid,@PathVariable String month,
			@PathVariable String year,@PathVariable String pmonth,
			@PathVariable String pyear) {
		
		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		Map<Object,Object> resmap=new HashMap();
		PreviewsScreen FreezedStatus=onePagerServiceImpl.getPreviewScreen(projectid,month+"-"+year.substring(2,4));
		resmap.put("pipIsFreezed",FreezedStatus==null?0: FreezedStatus.getIsFreezed());
		resmap.put("pipactiondata", pipActionService.getPipActionData(projectid,month,year,pmonth,pyear));
		resmap.put("pipfinantialdata", pipActionService.pipFinantialData(projectid,month,year,pmonth,pyear,(List<PipAction>)resmap.get("pipactiondata")));

		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}
	
	@PostMapping("/pip/savepipactiondata")
	public ResponseEntity<?> savepipactiondata(@RequestBody PipSaveJson parameter) {
		
		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		Map resmap=new HashMap();
		String successStatus=pipActionService.savePipActionData(parameter);
		resmap.put("savestatus", successStatus);
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}
}
