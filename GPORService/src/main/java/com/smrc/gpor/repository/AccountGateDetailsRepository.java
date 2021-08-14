package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.AccountGateDetails;
import com.smrc.gpor.model.AccountGateDetailsMapper;


@Repository
public interface AccountGateDetailsRepository extends JpaRepository<AccountGateDetails, Integer> {

	@Query("select new com.smrc.gpor.model.AccountGateDetailsMapper(agd.id,agd.commitGate,agd.value,agd.comment,agd.isFreezed,agd.mappingVariable) "
			+ "from AccountGateDetails agd where  agd.projectId = :projectId and agd.month = :month and agd.year = :year ")
	//List<AccountGateDetailsMapper> findByProjectIdAndMonthAndYear(Integer projectId, String month, String year);
	List<AccountGateDetailsMapper> findByProjectIdAndMonthAndYear(@Param("projectId") Integer projectId,
			@Param("month") String month, @Param("year") String year);
	
	@Query(" select a from AccountGateDetails a  where a.projectId=:projectId  and a.month=:month and a.year=:year   ")
	public  List<AccountGateDetails> findDataExistance(@Param("projectId") Integer projectId,@Param("month") String month,@Param("year") String year);

}
