package com.smrc.api.users.data;

import org.springframework.data.repository.CrudRepository;

public interface UserMenuRepository extends CrudRepository<MenuEntity, Long> {

	//MenuEntity findById(int )
	
}
