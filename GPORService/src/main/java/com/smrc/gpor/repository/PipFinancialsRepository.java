package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.PipFinancials;

//import com.smrc.gateone.model.PipFinancials;

@Repository
public interface PipFinancialsRepository extends JpaRepository<PipFinancials, Integer>{

	@Query(" select a from PipFinancials a where a.projectId=:projectId ")
	public List<PipFinancials> findPipFinancialDataByProject(@Param ("projectId") String projectId);
}
