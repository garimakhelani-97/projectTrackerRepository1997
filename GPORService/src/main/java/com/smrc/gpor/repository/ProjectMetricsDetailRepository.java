package com.smrc.gpor.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.ProjectMetricsDetail;

@Repository
public interface ProjectMetricsDetailRepository extends JpaRepository<ProjectMetricsDetail, Integer> {

	ProjectMetricsDetail findFirstByProjectIdAndReportedMonthAndMetricsId(Integer projectId, String reportedMonth,
			Integer metricsId, Sort sort);

	List<ProjectMetricsDetail> findByprojectId(Integer id);
	
	@Query(" select a from ProjectMetricsDetail a where a.metricsId=:metricsId and a.reportedMonth=:reportedMonth and a.projectId=:projectId")
	public List<ProjectMetricsDetail> getProjectMetricsDetailExistingdata(@Param("metricsId") Integer metricsId,@Param("reportedMonth") String reportedMonth
													,@Param("projectId") Integer projectId);

	
	 List<ProjectMetricsDetail> findAllByProjectIdAndReportedMonth(Integer id, String reportedMonth);
	 
    @Query("select a from ProjectMetricsDetail a where a.projectId=:id and a.reportedMonth=:previousReportingMonth")
	List<ProjectMetricsDetail> findAllByProjectIdAndPreviousMonth(@Param("id")Integer id,@Param("previousReportingMonth") String previousReportingMonth);

}
