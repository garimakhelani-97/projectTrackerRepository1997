package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smrc.gateone.model.PipFinancials;

public interface PipFinancialsRepository extends JpaRepository<PipFinancials, Integer>{
	
	public List<PipFinancials> findAllByProjectIdAndMappingVariable(Integer projectId, String mappingVariable);
	
	@Query(" select a from PipFinancials a where a.projectId=:projectId ")
	public List<PipFinancials> findPipFinancialDataByProject(@Param ("projectId") Integer projectId);
}
