package com.smrc.gpor.service;

import java.util.List;
import java.util.Map;

import com.smrc.gpor.dto.ProjectWithMetricsDTO;
import com.smrc.gpor.model.Currency;
import com.smrc.gpor.model.MonthlyExchangeRate;
import com.smrc.gpor.model.PreviewsScreen;



public interface OnePagerService {
	public String saveMetrics(List<ProjectWithMetricsDTO> myList);
	public Map getMetricsName(String projectId, String reportedMonth);
	
	public List<Currency> getCurrencyList();
	
	public MonthlyExchangeRate getMonthlyExchangeRate(String reportingPeriod, Integer sourceCurrencyId,
			Integer targetCurrencyId);
	
	public PreviewsScreen getPreviewScreen(Integer projectId, String reportedMonth);
	
	public void createPreviewScreen(Integer projectId, String reportedMonth);
	
	public Object getProjectApproversList(Integer projectId,String reportingPeriod, Integer currentUserId);
	
	public Map getMetricsDetails(String projecctId,String reportedMonth);
}
