package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.AccountMonthDetail;
import com.smrc.gpor.model.AccountMonthDetailMapper;

@Repository
public interface AccountMonthDetailRepository extends JpaRepository<AccountMonthDetail, Integer> {

	
	@Query("select new com.smrc.gpor.model.AccountMonthDetailMapper(amd.id,amd.value,amd.comment,amd.mappingVariable) "
			+ "from AccountMonthDetail amd where  amd.projectId = :projectId and amd.month = :month and amd.year = :year ")
	List<AccountMonthDetailMapper> findByProjectIdAndMonthAndYear(Integer projectId, String month, String year);
	
	
	@Query("select am from AccountMonthDetail am where am.projectId=:projectId and am.accountCode=:accountCode and month=:month and year=:year and mappingVariable=:mappingVariable")
		public 	AccountMonthDetail findByProjectIdAccountCodeMappingVar
		(@Param("projectId") Integer projectId,@Param("accountCode") String accountCode,@Param("month") String month,@Param("year") String year,@Param("mappingVariable") Integer mappingVariable);
}
