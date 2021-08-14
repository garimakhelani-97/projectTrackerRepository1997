package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.ExecutionSummaryDetails;

@Repository
public interface ExecutionSummaryDetailsRepository extends JpaRepository<ExecutionSummaryDetails,Integer> {

	List<ExecutionSummaryDetails> findAllByMonthAndYear(String month, String year);

	List<ExecutionSummaryDetails> findAllByKpiIdAndMonthAndYear(Integer kpiId, String month, String year);

}
