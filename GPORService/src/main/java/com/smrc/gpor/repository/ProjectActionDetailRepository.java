package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smrc.gpor.model.ProjectActionDetail;

public interface ProjectActionDetailRepository extends JpaRepository<ProjectActionDetail, Integer>{

	List<ProjectActionDetail> findAllByProjectIdAndMonthAndYearAndCreatedByIn(Integer parseInt, String month, String year,
			List<Integer> approverIds);
	
	@Query("select a from ProjectActionDetail a where a.projectId=:projectId and a.month=:month and a.year=:year")
	public ProjectActionDetail findByProjectIdMonthYear(@Param("projectId")Integer projectId, @Param("month")String month,
			@Param("year")String year);

	List<ProjectActionDetail> findAllByProjectIdAndMonthAndYear(int parseInt, String month, String year);
}
