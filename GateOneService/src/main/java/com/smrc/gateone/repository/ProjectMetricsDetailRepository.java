package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.ProjectMetricsDetail;
@Repository
public interface ProjectMetricsDetailRepository extends JpaRepository<ProjectMetricsDetail, Integer>{
	List<ProjectMetricsDetail> findByprojectId(Integer projectId);
	
	@Query(" select a from ProjectMetricsDetail a where a.metricsId=:metricsId  and a.projectId=:projectId")
	public List<ProjectMetricsDetail> getProjectMetricsDetailExistingdata(@Param("metricsId") Integer metricsId,@Param("projectId") Integer projectId);

	@Query("  select a from ProjectMetricsDetail a where a.projectId=:projectId and a.metricsId=:metricsId")
	public ProjectMetricsDetail findByProjectIdAndMetricId(@Param("projectId") Integer projectId,@Param("metricsId") Integer metricsId);
}
