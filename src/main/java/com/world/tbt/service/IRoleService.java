package com.world.tbt.service;

import com.world.tbt.dto.RoleDTO;
import com.world.tbt.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleService 
{
	List<RoleDTO> findAll();
	Long getTotalItem();
	void delete(long[] ids);
	RoleDTO saveOrUpdate(RoleDTO dto);

	@Transactional
	ResponseEntity<?> deleteRoles(long[] ids);

	List<RoleDTO> findByNameContaining(String roleName);

	Page<RoleDTO> findByPage(Pageable pageable);

	Page<RoleDTO> convertPageEntityToPageDTO(Page<RoleEntity> entities);

	RoleDTO findById(long roleid);
}
