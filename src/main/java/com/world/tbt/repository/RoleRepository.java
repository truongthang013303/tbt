package com.world.tbt.repository;

import com.world.tbt.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>
{	
	RoleEntity findOneByCode(String code);	
	RoleEntity findOneById(long id);
	boolean existsByCode(String code);
	List<RoleEntity> findByNameContaining(String roleName);
}
