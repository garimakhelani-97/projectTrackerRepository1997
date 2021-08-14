package com.smrc.gateone.serviceImpl;

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

import com.smrc.gateone.model.Project;

@Service

public class SyncGPORServiceImpl {
	@Value("${zuulURL}")
	private String zuulURL;
	
	public String addProjectProcessAndTransactionsData(Map requestBodyMap) {
		final String uri = zuulURL + "/gpor-ws/sync/addProjectProcessAndTransactionsData";
		HttpEntity<Map> entity = null;
		HttpHeaders headers = null;
		RestTemplate restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		entity = new HttpEntity<>(requestBodyMap, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(uri, HttpMethod.POST, entity, HashMap.class);

		System.out.println(response);
		return "";
		
	}
	
	public String updateProjectWhenItsGPOR(Project project) {
		final String uri = zuulURL + "/gpor-ws/sync/updateProjectWhenItsGPOR";
		HttpEntity<Map> entity = null;
		HttpHeaders headers = null;
		RestTemplate restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Map requestMap=new HashMap();
		requestMap.put("project", project);
		entity = new HttpEntity<>(requestMap, headers);
		ResponseEntity<HashMap> response = restTemplate.exchange(uri, HttpMethod.POST, entity, HashMap.class);

		System.out.println(response);
		return "";
	}

}