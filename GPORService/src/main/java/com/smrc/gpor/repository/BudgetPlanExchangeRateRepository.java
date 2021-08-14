package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smrc.gpor.model.BudgetPlanExchangeRate;
import com.smrc.gpor.model.MonthlyExchangeRate;

public interface BudgetPlanExchangeRateRepository  extends JpaRepository<BudgetPlanExchangeRate, Integer>{

	@Query("select a from BudgetPlanExchangeRate a where a.reportingYear=:reportingPeriod and a.sourceCurrencyId=:sourceCurrencyId and  "
			+ " a.targetCurrencyId=4 ")
	public BudgetPlanExchangeRate findByReportingPeriodAndSourceCurrerncyId(@Param ("reportingPeriod") String reportingPeriod,
																			@Param ("sourceCurrencyId") Integer sourceCurrencyId);
	
}
