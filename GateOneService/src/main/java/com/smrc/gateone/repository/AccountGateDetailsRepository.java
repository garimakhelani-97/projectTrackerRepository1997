package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.dto.AccountGateDetailsMapper;
import com.smrc.gateone.model.AccountGateDetails;


@Repository
public interface AccountGateDetailsRepository extends JpaRepository<AccountGateDetails, Integer> {

	@Query("select new com.smrc.gateone.dto.AccountGateDetailsMapper(agd.id,agd.commitGate,agd.value,agd.comment,agd.isFreezed,agd.mappingVariable,agd.securedProfitability) "
			+ "from AccountGateDetails agd where  agd.projectId = :projectId ")
	//List<AccountGateDetailsMapper> findByProjectIdAndMonthAndYear(Integer projectId, String month, String year);
	List<AccountGateDetailsMapper> findByProjectIdAndMonthAndYear(@Param("projectId") Integer projectId);
	
	@Query(" select a from AccountGateDetails a  where a.projectId=:projectId  and a.id=:id ")
	public  AccountGateDetails findDataExistance(@Param("projectId") Integer projectId,@Param("id") Integer id);
	
	@Query(" select a from AccountGateDetails a  where a.projectId=:projectId  ")
	public  List<AccountGateDetails> findAllByprojectId(@Param("projectId") Integer projectId);

}