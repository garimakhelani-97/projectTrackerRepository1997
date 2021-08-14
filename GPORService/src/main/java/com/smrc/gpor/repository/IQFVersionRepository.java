package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smrc.gpor.model.IQFVersion;

public interface IQFVersionRepository extends JpaRepository<IQFVersion, Integer>{
	
	List<IQFVersion> findAllByOrderByIdDesc();

}
