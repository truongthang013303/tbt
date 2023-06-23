package com.world.tbt.service.impl;

import com.world.tbt.converter.RoleConverter;
import com.world.tbt.dto.RoleDTO;
import com.world.tbt.entity.RoleEntity;
import com.world.tbt.repository.RoleRepository;
import com.world.tbt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class RoleService implements IRoleService
{
	@Autowired
	private RoleConverter roleConverter;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<RoleDTO> findAll() 
	{
		List<RoleEntity> listRoleEntity = roleRepository.findAll();
		List<RoleDTO> listRoleDTO = new ArrayList<RoleDTO>();
		for (RoleEntity roleEntity : listRoleEntity) {
			listRoleDTO.add(roleConverter.toDto(roleEntity));
		}
		return listRoleDTO;
	}
	
	@Override
	public RoleDTO findById(long id)
	{		
		return roleConverter.toDto(roleRepository.findOneById(id));
	}
	@Override
	public Long getTotalItem()
	{	
		return roleRepository.count();
	}

	@Override
	@Transactional
	public RoleDTO saveOrUpdate(RoleDTO dto)
	{
		RoleEntity roleEntity = new RoleEntity();		
		if(dto.getId()!=null) 
		{
			RoleEntity oldRole = roleRepository.findOneById(dto.getId());
			roleEntity = roleConverter.toEntity(oldRole,dto);
		}
		else if(roleRepository.existsByCode(dto.getCode())==false) //roleRepository.findExistByCode(dto.getCode())==0
		{
			roleEntity = roleConverter.toEntity(dto);
			System.out.println("Chua ton tai");
		}
		else
		{
			System.out.println("Da ton tai");
			return null;
		}
		RoleEntity roleEntitySaved = roleRepository.save(roleEntity);
		return roleConverter.toDto(roleEntitySaved);
	}
	
	@Override
	@Transactional
	public void delete(long[] ids) 
	{
		if(ids.length!=0)
		{
			RoleEntity roleEntity = roleRepository.findOneByCode("ROlE_ADMIN");
			long[] idsValid = Arrays.stream(ids).filter(r->r!=roleEntity.getId()).toArray();
			for (long id: idsValid)
			{
				roleRepository.deleteById(id);
			}
		}
	}
	@Override
	@Transactional
	public ResponseEntity<?> deleteRoles(long[] ids)
	{
		if(ids.length!=0)
		{
			RoleEntity roleAdmin = roleRepository.findOneByCode("ROlE_ADMIN");
			RoleEntity roleUser = roleRepository.findOneByCode("ROlE_USER");
			long[] idsValid = Arrays.stream(ids).filter(r->r!=roleAdmin.getId()&&r!=roleUser.getId()).toArray();
			if(idsValid.length==0){
				return ResponseEntity.badRequest().body("ROLE_ADMIN, ROLE_USER cant be remove");
			}
			if(ids.length!=idsValid.length){
				return ResponseEntity.ok().body("List of roles to be remove has ROLE_ADMIN, ROLE_USER cant be remove");
			}
			for (long id: idsValid)
			{
				if(roleRepository.existsById(id)){
					roleRepository.deleteById(id);
				}
				else{
					return ResponseEntity.badRequest().body("id:"+id+" is not exist");
				}
			}
		}else{
			return ResponseEntity.badRequest().body("List of roles is empty");
		}
		return ResponseEntity.ok().body("succeeded");
	}

	@Override
	public List<RoleDTO> findByNameContaining(String roleName) {
		List<RoleEntity> roles = roleRepository.findByNameContaining(roleName);
		List<RoleDTO> dtos = roles.stream().map(c -> roleConverter.toDto(c)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public Page<RoleDTO> findByPage(Pageable pageable) {
		Page<RoleEntity> entities = roleRepository.findAll(pageable);
		Page<RoleDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}

	@Override
	public Page<RoleDTO> convertPageEntityToPageDTO(Page<RoleEntity> entities)
	{
		Page<RoleDTO> dtoPage = entities.map(new Function<RoleEntity, RoleDTO>() {
			@Override
			public RoleDTO apply(RoleEntity entity) {
				RoleDTO dto = new RoleDTO();
				dto = roleConverter.toDto(entity);
				return dto;
			}
		});
		return dtoPage;
	}
}
