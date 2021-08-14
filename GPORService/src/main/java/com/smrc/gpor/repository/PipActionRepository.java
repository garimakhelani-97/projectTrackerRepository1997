package com.smrc.gpor.repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.PipAction;


@Repository
@Transactional
public interface PipActionRepository extends JpaRepository<PipAction, Integer>{

	@Query(" select a from PipAction a where a.project.id=:projectId and a.month=:month and a.year=:year ")
	public List<PipAction> findPipActionDataByProject(@Param ("projectId") Integer projectId,@Param ("month") String month ,@Param ("year") String year);
	
	@Query("  select a from PipAction a where a.project.id=:projectId and a.month=:month and a.year=:year ")
	public List<PipAction> findDataAgainstProjectidMonthYear(@Param ("projectId") Integer projectId,@Param ("month") String month,@Param ("year") String year );
	
	@Query("  select distinct a.mappingVariable as mappingVariable,a.shortHeadName as shortHeadName ,b.value as currentvalue,c.value as gate1commitvalue"
			+ " ,b.comment as comment,b.id as id"
			+ "  from ReportAccountsMapping_PIP a,AccountMonthDetail_PIP  b,AccountGateDetails_PIP c "
			+ "  where a.shortHeadName in('Total EBIT % of Sales','IRR','ROCE') and a.accountType='gpor_input' "
			+ " and a.mappingVariable=b.mappingVariable and a.mappingVariable=c.mappingVariable"
			+ "  and b.month=:month and b.year=:year and c.projectId=:projectId  and c.month=b.month and c.year=b.year"
			+ " and b.projectId=c.projectId order by a.shortHeadName  ")
	public List<Map> getMappingVariablesAgainstAccountCode(@Param ("month") String month,@Param ("year") String year,@Param ("projectId") Integer projectId);
}

