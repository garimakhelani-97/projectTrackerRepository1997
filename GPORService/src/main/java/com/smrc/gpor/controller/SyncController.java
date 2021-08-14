package com.smrc.gpor.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.response.ObjectResponse;
import com.smrc.gpor.serviceImpl.SyncServiceImpl;

@RestController
@RequestMapping("/sync")
public class SyncController {

	@Autowired
	private SyncServiceImpl syncServiceImpl;
	@PostMapping("/addProject")
	public ResponseEntity<ObjectResponse<Object>> geProcessWiseProjectList(@RequestBody Map  parameter) {

		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		syncServiceImpl.addProject((Map)parameter.get("projectMapper"),parameter.get("projectID").toString(),(Map)parameter.get("projectProcessAssociation"));
		Map<Object,Object> resmap=new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}
	
	@PostMapping("/updateProject")
	public ResponseEntity<ObjectResponse<Object>> updateProject(@RequestBody Map parameter) {
		ObjectResponse<Object> endPointResponse = new ObjectResponse();
		this.syncServiceImpl.updateProject((Map)parameter.get("projectMapper"), parameter.get("projectID").toString(), (Boolean)parameter.get("recordStatusID"), (Map)parameter.get("projectProcessAssociation"));
		Map<Object, Object> resmap = new HashMap<>();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);
		}
	/*public ResponseEntity<ObjectResponse<Object>> updateProject(@RequestBody Map  parameter) {

		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		syncServiceImpl.updateProject((Map)parameter.get("projectMapper"),parameter.get("projectID").toString(),(Boolean)parameter.get("recordStatusID"),(Map)parameter.get("projectProcessAssociation"));
		Map<Object,Object> resmap=new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

<<<<<<< HEAD
	}*/
	
	
	@PostMapping("/updateProjectImages")
	public ResponseEntity<ObjectResponse<Object>> updateProjectImages(@RequestBody Map  parameter) {

		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		syncServiceImpl.updateProjectImages((Map)parameter.get("projectMapper"),parameter.get("projectID").toString());
		Map<Object,Object> resmap=new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}
	
	@PostMapping("/addProjectProcessAndTransactionsData")
	public ResponseEntity<ObjectResponse<Object>> addProjectProcessAndTransactionsData(@RequestBody Map  parameter) throws ParseException {

		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		syncServiceImpl.addProjectProcessAndTransactionsData(parameter);
		Map<Object,Object> resmap=new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}

	@PostMapping("/updateProjectWhenItsGPOR")
	public ResponseEntity<ObjectResponse<Object>> updateProjectWhenItsGPOR(@RequestBody Map  parameter) throws ParseException {

		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		syncServiceImpl.updateProjectWhenItsGPOR(parameter);
		Map<Object,Object> resmap=new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}


	}


