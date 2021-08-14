package com.smrc.mdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.Status;



@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
	
	List<Status> findByStatusIgnoreCase(String status);

}
