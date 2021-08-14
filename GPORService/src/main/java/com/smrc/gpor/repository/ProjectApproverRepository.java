package com.smrc.gpor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.smrc.gpor.model.ProjectApprover;

public interface ProjectApproverRepository extends JpaRepository<ProjectApprover, Integer>{

	public List<ProjectApprover> findAllByProjectId(Integer projectId);

	public List<ProjectApprover> findAllByProjectIdAndMonthAndYear(Integer projectId, String month, String year);

	
	@Query("select a from ProjectApprover a where a.projectId=:projectId and a.month=:month and a.year=:year and a.approveStatus=:approverStatusId order by a.orderId   ")
	public List<ProjectApprover> getApprovedBy(Integer projectId, String month, String year, Integer approverStatusId );

	@Query("select a from ProjectApprover a where a.projectId=:projectId and a.month=:month and a.year=:year and a.approvedBy=:userId")
	public ProjectApprover findByProjectIdMonthYearUserId(@Param("projectId")Integer projectId, @Param("month")String month,
			@Param("year") String year,@Param("userId") Integer parseInt);
	
	@Query("select a from ProjectApprover a where a.projectId=:projectId and a.month=:month and a.year=:year and a.approveStatus=:approverStatusId and a.orderId=2")
    public List<ProjectApprover> getApprovedstatus(@Param("projectId")Integer projectId, @Param("month")String month,
            @Param("year") String year,@Param("approverStatusId") Integer approverStatusId);
	
	@Query("select a from ProjectApprover a where a.orderId=2 and approveStatus=18 and a.projectId=:projectId "
			+ " and a.month=:month and a.year=:year")
	public List<ProjectApprover> lastMonthApprovedStatus(@Param("projectId")Integer projectId, @Param("month")String month,
            @Param("year") String year);

	public List<ProjectApprover> findAllByProjectIdAndMonthAndYearAndStatusIdAndApproveStatus(Integer projectId, String month,
			String year, Integer statusId, Integer approveStatus);

	public List<ProjectApprover> findAllByProjectIdAndMonthAndYearAndStatusId(Integer projectId, String month,
			String year, Integer ACTIVE);

	public ProjectApprover findByProjectIdAndMonthAndYearAndApprovedByAndStatusId(Integer projectId, String month, String year,
			Integer parseInt, Integer statusId);

	public Optional<ProjectApprover> findByProjectIdAndMonthAndYearAndOrderIdAndStatusId(Integer projectId,
			String month, String year, Integer incrementedOrderId, Integer statusId);
}
