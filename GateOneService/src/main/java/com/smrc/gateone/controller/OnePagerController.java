package com.smrc.gateone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smrc.gateone.model.BudgetPlanExchangeRate;
import com.smrc.gateone.model.Currency;
import com.smrc.gateone.model.PreviewsScreen;
import com.smrc.gateone.serviceImpl.OnePagerServiceImpl;

@RestController
//@RequestMapping("/onePager")
public class OnePagerController {
	
	@Autowired
	private OnePagerServiceImpl onePagerServiceImpl;
	
	// get currency list 
	@GetMapping("/getCurrency")
	public List<Currency> getCurrencyList() {
		List<Currency> currencyList = onePagerServiceImpl.getCurrencyList();
		return currencyList;
	}
	
	//get monthlyExchangeRate by reportingPeriod sourceCurrency and targetCurrency 
	@GetMapping("/getMonthlyExchangeRate")
	public BudgetPlanExchangeRate getMonthlyExchangeRate(@RequestParam String reportingPeriod,
			@RequestParam String sourceCurrencyId, @RequestParam String targetCurrencyId) {

		BudgetPlanExchangeRate monthlyExchangeRate = onePagerServiceImpl.getMonthlyExchangeRate(reportingPeriod,
				Integer.parseInt(sourceCurrencyId), Integer.parseInt(targetCurrencyId));
		return monthlyExchangeRate;
	}
	
	//get PreviewScreen
	@GetMapping("/previewScreen")
	ResponseEntity<PreviewsScreen> getPreviewScreen(@RequestParam String projectId) {

		PreviewsScreen previewScreen = onePagerServiceImpl.getPreviewScreen(Integer.parseInt(projectId));

		return ResponseEntity.ok(previewScreen);
	}
	//create preview Screen 
	@PostMapping("addPreviewScreen")
	public ResponseEntity<String> createPreviewScreen(@RequestParam String projectId) {
		
		onePagerServiceImpl.createPreviewScreen(Integer.parseInt(projectId));
		
		return ResponseEntity.ok().build();

	}
}
