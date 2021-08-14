package com.smrc.api.users.data;

import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<RoleEntity, Long> {

	RoleEntity findById(int id);
	
}
