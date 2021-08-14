package com.smrc.gateone.service;

import java.util.List;

import com.smrc.gateone.model.Currency;
import com.smrc.gateone.model.BudgetPlanExchangeRate;
import com.smrc.gateone.model.PreviewsScreen;


public interface OnePagerService {
	
	public List<Currency> getCurrencyList();
	
	public BudgetPlanExchangeRate getMonthlyExchangeRate(String reportingPeriod, Integer sourceCurrencyId,
			Integer targetCurrencyId);
	
	public PreviewsScreen getPreviewScreen(Integer projectId);
	
	public void createPreviewScreen(Integer projectId);
}
