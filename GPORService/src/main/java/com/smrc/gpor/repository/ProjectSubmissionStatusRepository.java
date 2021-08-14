package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smrc.gpor.model.ProjectSubmissionStatus;


public interface ProjectSubmissionStatusRepository extends JpaRepository<ProjectSubmissionStatus, Integer>{

	@Query("select a.submissionFlag from ProjectSubmissionStatus a where a.projectId=:projectId AND a.month=:month AND a.year=:year ")
	Integer getSubmissionFlagByIdAndMonthAndYear(@Param("projectId")Integer formatttedProjectId, @Param("month")String month,
			@Param("year")String year);
	
	@Query("select a.submittedBy from ProjectSubmissionStatus a where a.projectId=:projectId AND a.month=:month AND a.year=:year ")
	Integer getsubmittedByIdAndMonthAndYear(@Param("projectId")Integer formatttedProjectId, @Param("month")String month,
			@Param("year")String year);
			
	@Query("select a from ProjectSubmissionStatus a where a.projectId=:projectId and a.month=:month and a.year=:year")
	public ProjectSubmissionStatus findDataByProjectIdMonthYear(@Param("projectId")Integer projectId, @Param("month")String month,
			@Param("year")String year);
	
	@Query("select a from ProjectSubmissionStatus a where a.projectId=:projectId and a.month=:month and a.year=:year ")
    public List<ProjectSubmissionStatus> findsubmissionStatus(@Param("projectId")Integer projectId, @Param("month")String month,
            @Param("year")String year);

	ProjectSubmissionStatus findByProjectIdAndMonthAndYear(Integer projectId, String month, String year);
}
