package com.smrc.gateone.controller;

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

import com.smrc.gateone.model.PipAction;
import com.smrc.gateone.model.PipSaveJson;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.response.ObjectResponse;
import com.smrc.gateone.service.PipActionService;
import com.smrc.gateone.serviceImpl.OnePagerServiceImpl;


@RestController
public class PipActionController {

	@Value("${pipActionDisable}")
	private String pipIsFreezedStatus;
	@Autowired
	private PipActionService pipActionService;
	@Autowired
	private OnePagerServiceImpl onePagerServiceImpl;

	@GetMapping("/pip/getpipactiondata/{projectid}")
	public ResponseEntity<ObjectResponse<Object>> getPipActionData(@PathVariable Integer projectid) {
		
		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		Map<Object,Object> resmap=new HashMap<Object,Object>();
		PreviewsScreen FreezedStatus=onePagerServiceImpl.getPreviewScreen(projectid);
		resmap.put("pipIsFreezed",FreezedStatus==null?0: FreezedStatus.getIsFreezed());
		resmap.put("pipactiondata", pipActionService.getPipActionData(projectid));
		resmap.put("pipfinantialdata", pipActionService.pipFinantialData(projectid, (List<PipAction>)resmap.get("pipactiondata")));

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
