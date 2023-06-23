package com.world.tbt.service;

import com.world.tbt.dto.UserDTO;
import com.world.tbt.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService 
{
    UserEntity rating(Long postid, Long userid, Integer rating);
	Long getTotalItem();
	UserDTO findById(long id);
	UserDTO saveOrUpdate(UserDTO dto);
	void delete(long[] ids);
	UserDTO changeStatusUser(Optional<Long> id, Optional<Integer> status);
	ResponseEntity<?> changeStatusUserResponseEntity(Optional<Long> id, Optional<Integer> status);

	List<UserDTO> findByUserNameContaining(String username);

	Page<UserDTO> findByPage(Pageable pageable);

	Page<UserDTO> convertPageEntityToPageDTO(Page<UserEntity> entities);

    Page<UserDTO> findByRolesIn(Long roleid, Pageable pageable);
}
