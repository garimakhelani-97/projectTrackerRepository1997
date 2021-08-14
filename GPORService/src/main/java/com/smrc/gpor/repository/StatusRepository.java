package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.Status;



@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

	List<Status> findAllByIdIn(List<Integer> statusId);
	
	@Query("select a.description from Status as a where a.id=:statusId")
	String getStatus(@Param("statusId")Integer statusId);

}
