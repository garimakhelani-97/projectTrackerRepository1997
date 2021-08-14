package com.smrc.gpor.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.model.PipAction;
import com.smrc.gpor.model.PreviewsScreen;
import com.smrc.gpor.response.ObjectResponse;

@RestController
@RequestMapping("/timezone")
public class TimeZoneController {

	@GetMapping("Europe/Tirane")
	public ResponseEntity<ObjectResponse<Object>> getPipActionData() {
		
		final ObjectResponse<Object> endPointResponse=new ObjectResponse<>();
		Map<Object,Object> resmap=new HashMap();
		Instant timeStamp= Instant.now();
		 ZonedDateTime LAZone= timeStamp.atZone(ZoneId.of("Europe/Tirane"));  
		 resmap.put("EuropeTirane", LAZone);
		endPointResponse.setResponse(resmap);
		endPointResponse.setStatus("success");
		return ResponseEntity.status(HttpStatus.OK).body(endPointResponse);

	}
	
}
