package com.smrc.mdm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.mdm.model.FileMetaData;

@Repository
public interface FileMetaDataRepository  extends JpaRepository<FileMetaData, Integer>{
	
	List<FileMetaData> findByImageType(String imageType);

}
