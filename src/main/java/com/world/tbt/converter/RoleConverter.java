package com.world.tbt.converter;
import com.world.tbt.dto.RoleDTO;
import com.world.tbt.entity.PrivilegeEntity;
import com.world.tbt.entity.RoleEntity;
import com.world.tbt.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

@Component
public class RoleConverter {
	@Autowired
	PrivilegeRepository privilegeRepository;
	public RoleDTO toDto(RoleEntity entity) {
		RoleDTO result = new RoleDTO();
		result.setId(entity.getId());
		result.setName(entity.getName());
		result.setCode(entity.getCode());
		Collection<String> privileges=new ArrayList<String>();
		entity.getPrivileges().forEach(s->privileges.add(s.getCode()));
		result.setPrivilegeCode(privileges);
		result.setCreatedBy(entity.getCreatedBy());
		result.setCreatedDate(entity.getCreatedDate());
		result.setModifiedBy(entity.getModifiedBy());
		result.setModifiedDate(entity.getModifiedDate());
		return result;
	}
	
	public RoleEntity toEntity(RoleDTO dto) {
		RoleEntity result = new RoleEntity();
		result.setName(dto.getName());
		result.setCode(dto.getCode());
		Collection<PrivilegeEntity> privileges=new LinkedHashSet<PrivilegeEntity>();
		if(dto.getPrivilegeCode().isEmpty()!=true)
		{
			for(String privilegeCodes:dto.getPrivilegeCode())
			{
				PrivilegeEntity privi = privilegeRepository.findOneByCode(privilegeCodes);
				privileges.add(privi);
			}
		}
		result.setPrivileges(privileges);
		return result;
	}

	public RoleEntity toEntity(RoleEntity oldRoleEntity, RoleDTO dto)
	{
		oldRoleEntity.setName(dto.getName());
		oldRoleEntity.setCode(dto.getCode());

		Collection<PrivilegeEntity> privileges=new LinkedHashSet<PrivilegeEntity>();
		if(dto.getPrivilegeCode().isEmpty()!=true)
		{
			for(String privilegeCodes:dto.getPrivilegeCode())
			{
				PrivilegeEntity privi = privilegeRepository.findOneByCode(privilegeCodes);
				privileges.add(privi);
			}
		}
		oldRoleEntity.setPrivileges(privileges);
		return oldRoleEntity;
	} 
}
