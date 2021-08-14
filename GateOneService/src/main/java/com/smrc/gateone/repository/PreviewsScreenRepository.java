package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.PreviewsScreen;


@Repository
public interface PreviewsScreenRepository extends JpaRepository<PreviewsScreen, Integer>{
	
	public PreviewsScreen findByProjectId(Integer projectId);

	
	@Query("select a from PreviewsScreen a where a.projectId IN (:projectIds)")
	public List<PreviewsScreen> findAllByprojectId(@Param ("projectIds") List<Integer> projectIds);

	//public List<PreviewsScreen> findAllByprojectId(List<Integer> projectIds);

}
