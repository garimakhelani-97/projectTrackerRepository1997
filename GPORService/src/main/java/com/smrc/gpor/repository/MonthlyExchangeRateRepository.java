package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.MonthlyExchangeRate;

@Repository
public interface MonthlyExchangeRateRepository extends JpaRepository<MonthlyExchangeRate, Integer>{
	
	public MonthlyExchangeRate findByReportingPeriodAndSourceCurrerncyIdAndTargetCurrenyId(String reportingPeriod,
			Integer sourceCurrerncyId, Integer targetCurrenyId);
	
}
