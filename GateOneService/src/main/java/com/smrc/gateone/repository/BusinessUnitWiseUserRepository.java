package com.smrc.gateone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smrc.gateone.model.BusinessUnitWiseUser;

@Repository
public interface BusinessUnitWiseUserRepository  extends JpaRepository<BusinessUnitWiseUser, Integer>{

	@Query("select a.userId from BusinessUnitWiseUser a where a.customerGroupId=:customerGroupId")
	List<Integer> getUserIdByBuId(@Param ("customerGroupId")Integer customerGroupId);

}
