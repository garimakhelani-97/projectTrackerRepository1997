package com.smrc.gateone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.dto.ProcessAssignMapper;
import com.smrc.gateone.dto.ProjectFinancialResDTO;
import com.smrc.gateone.model.ProjectProcessAssociationModel;
import com.smrc.gateone.service.AssignProcessService;

@RestController
@RequestMapping("gateone/")
public class AssignProcessController {
	@Autowired
	AssignProcessService assignProcessService;
   
	@GetMapping("/getProcessAssociationData")
	ProjectProcessAssociationModel getProcessAssociationData(@RequestParam String projectId) {
		return assignProcessService.getProcessAssociationData(projectId);
	}
	
	@PostMapping("/saveProcessAssociationData/{projectId}")
	ResponseEntity<String> saveProcessAssociationData(@PathVariable String projectId,
			@RequestBody Map processAssignMapper) {
		String response= assignProcessService.saveProcessAssociationData(projectId,processAssignMapper);
	return ResponseEntity.ok().body(response);
	}
}
