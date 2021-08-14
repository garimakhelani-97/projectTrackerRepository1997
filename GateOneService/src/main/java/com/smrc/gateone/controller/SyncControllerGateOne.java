package com.smrc.gateone.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.response.ObjectResponse;
import com.smrc.gateone.serviceImpl.SyncServiceImplGateOne;

//import com.smrc.gpor.response.ObjectResponse;
@RestController
@RequestMapping("/sync")
public class SyncControllerGateOne {
	@Autowired
	private SyncServiceImplGateOne syncServiceImplGateOne;

	@PostMapping("/addProject")
	public ResponseEntity<ObjectResponse<Object>> geProcessWiseProjectList(@RequestBody Map parameter) {

		final ObjectResponse<Object> endPointResponse = new ObjectResponse<>();
		syncServiceImplGateOne.addProject((Map) parameter.get("projectMapper"), parameter.get("projectID").toString());
		Map<Object, Object> resmap = new HashMap();
		resmap.put("data", "data");
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}

}
