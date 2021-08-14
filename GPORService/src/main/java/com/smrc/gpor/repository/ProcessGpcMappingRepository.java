package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.CustomerGroupUserMapping;
import com.smrc.gpor.model.ProcessGpcMapping;

@Repository
public interface ProcessGpcMappingRepository extends JpaRepository<ProcessGpcMapping, Integer>{

	 @Query("select a.userId from ProcessGpcMapping a  where a.processId=:processId")
	public Integer findByProcessId(@Param("processId")Integer processId);

	
	}


