package com.smrc.gpor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gpor.dto.ProjectWithMetricsDTO;
import com.smrc.gpor.model.BudgetPlanExchangeRate;
import com.smrc.gpor.model.Currency;
import com.smrc.gpor.model.MonthlyExchangeRate;
import com.smrc.gpor.model.PreviewsScreen;
import com.smrc.gpor.serviceImpl.OnePagerServiceImpl;


@RestController
//@RequestMapping("/onePager")
public class OnePagerController {
	
	@Autowired
	private OnePagerServiceImpl onePagerServiceImpl;
	
	@GetMapping("/getMetricsName")
	public Map<String, Object> getMetricsName(@RequestParam String projectId,
			@RequestParam String reportedMonth){
		
		return onePagerServiceImpl.getMetricsName(projectId,reportedMonth);
	}
	
	@PostMapping("/addMetricsData")
	ResponseEntity<String> addMetrics(@RequestBody List<ProjectWithMetricsDTO> projectMetricsDTOList) {
		onePagerServiceImpl.saveMetrics(projectMetricsDTOList);
		return ResponseEntity.ok().build();
	}

	// get currency list 
	@GetMapping("/getCurrency")
	public List<Currency> getCurrencyList() {
		List<Currency> currencyList = onePagerServiceImpl.getCurrencyList();
		return currencyList;
	}
	
	//get monthlyExchangeRate by reportingPeriod sourceCurrency and targetCurrency 
	@GetMapping("/getMonthlyExchangeRate")
	public MonthlyExchangeRate getMonthlyExchangeRate(@RequestParam String reportingPeriod,
			@RequestParam String sourceCurrencyId, @RequestParam String targetCurrencyId) {

		MonthlyExchangeRate monthlyExchangeRate = onePagerServiceImpl.getMonthlyExchangeRate(reportingPeriod,
				Integer.parseInt(sourceCurrencyId), Integer.parseInt(targetCurrencyId));
		return monthlyExchangeRate;
	}
	
	
	@GetMapping("/getYearlyExchangeRate")
	public BudgetPlanExchangeRate getYearlyExchangeRate(@RequestParam String reportingPeriod,
			@RequestParam String sourceCurrencyId) {

		BudgetPlanExchangeRate monthlyExchangeRate = onePagerServiceImpl.getYearlyExchangeRate(reportingPeriod,
				Integer.parseInt(sourceCurrencyId));
		return monthlyExchangeRate;
	}
	
	//get PreviewScreen
	@GetMapping("/previewScreen")
	ResponseEntity<PreviewsScreen> getPreviewScreen(@RequestParam String projectId, @RequestParam String reportedMonth) {

		PreviewsScreen previewScreen = onePagerServiceImpl.getPreviewScreen(Integer.parseInt(projectId), reportedMonth);

		return ResponseEntity.ok(previewScreen);
	}
	//create preview Screen 
	@PostMapping("addPreviewScreen")
	public ResponseEntity<String> createPreviewScreen(@RequestParam String projectId, @RequestParam String reportedMonth) {
		
		onePagerServiceImpl.createPreviewScreen(Integer.parseInt(projectId), reportedMonth);
		
		return ResponseEntity.ok().build();

	}
	/* Get project approvers list */
	@GetMapping("/getProjectApproverListById")
	public ResponseEntity<Object> getProjectApproversList(@RequestParam  String projectId, @RequestParam String reportingPeriod,
			HttpServletRequest req){
		Integer currentUserId=Integer.parseInt(req.getHeader("userid"));
		Object customList = onePagerServiceImpl.getProjectApproversList(Integer.parseInt(projectId),reportingPeriod,currentUserId);
		return ResponseEntity.ok(customList);
	}
	
	@GetMapping("/getMetricsData")
	public Map<String, Object> getMetricsData(@RequestParam String projectId,
			@RequestParam String reportedMonth){
		
		return onePagerServiceImpl.getMetricsDetails(projectId,reportedMonth);
	}
	
}
