package com.smrc.mdm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.smrc.mdm.model.ProjectMapper;
import com.smrc.mdm.model.ProjectProcessAssociation;

@Service
public class SyncGateOneServiceImpl {

	@Value("${zuulURL}")
	private String zuulURL;

	public String addProjectRequestForGateOne(ProjectMapper projectMapper, String projectID) {

		Map projectMap = new HashMap();
		projectMap.put("projectMapper", projectMapper);
		projectMap.put("projectID", projectID);
//		projectMap.put("projectProcessAssociation", saveEntityProjectProcessAssociation);
		final String uri = zuulURL + "/gateone-ws/sync/addProject";
		HttpEntity<Map> entity = null;
		HttpHeaders headers = null;
		RestTemplate restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<>(projectMap, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(uri, HttpMethod.POST, entity, HashMap.class);

		System.out.println(response);
		return "";
	}

}
