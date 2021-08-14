package com.smrc.gateone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.BudgetPlanExchangeRate;

@Repository
public interface MonthlyExchangeRateRepository extends JpaRepository<BudgetPlanExchangeRate, Integer>{
	
	public BudgetPlanExchangeRate findByReportingYearAndSourceCurrencyIdAndTargetCurrencyId(String reportingYear,
			Integer sourceCurrerncyId, Integer targetCurrenyId);
	
}
