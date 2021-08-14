package com.smrc.gpor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.MailBox;



@Repository
public interface SubmitMailRepository extends JpaRepository<MailBox, Integer>{ 
	
	

}
