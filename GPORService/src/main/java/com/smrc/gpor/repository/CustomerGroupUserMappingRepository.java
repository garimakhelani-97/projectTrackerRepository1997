package com.smrc.gpor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gpor.model.CustomerGroupUserMapping;

@Repository
public interface CustomerGroupUserMappingRepository extends JpaRepository<CustomerGroupUserMapping, Integer> {

	 @Query("select a.userId from CustomerGroupUserMapping a  where a.customerGroupId=:customerGroupId")
	public Integer findByCustomerGroupId(@Param("customerGroupId")Integer customerGroupId);
	 
	 @Query(" select a from CustomerGroupUserMapping a where a.userId=:userId ")
	 public List<CustomerGroupUserMapping> findAllCustomerGroups(@Param ("userId")Integer userId);
   
}
