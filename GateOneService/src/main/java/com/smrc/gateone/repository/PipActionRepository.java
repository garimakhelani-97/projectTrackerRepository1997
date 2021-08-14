package com.smrc.gateone.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.PipAction;


@Repository
@Transactional
public interface PipActionRepository extends JpaRepository<PipAction, Integer>{

	@Query(" select a from PipAction a where a.project.id=:projectId ")
	public List<PipAction> findPipActionDataByProject(@Param ("projectId") Integer projectId);
		
	@Query("  select distinct a.mappingVariable as mappingVariable,a.shortHeadName as shortHeadName "
			+ ",c.securedProfitability as securedProfitability"
			+ ", c.commitGate as commitGate"
			+ ", c.value as value"
			+ ", a.misAccountCode as misAccountCode"
			+ " ,c.comment as comment"
			+ "  from ReportAccountsMapping_PIP a,AccountGateDetail c "
			+ "  where a.misAccountCode in('8012','8009','8008') and a.accountType='gpor_input' "
			+ " and a.mappingVariable=c.mappingVariable"
			+ "  and c.projectId=:projectId  "
			+ " order by a.shortHeadName  ")
	public List<Map> getMappingVariablesAgainstAccountCode(@Param ("projectId") Integer projectId);
}

