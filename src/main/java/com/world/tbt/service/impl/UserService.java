package com.world.tbt.service.impl;

import com.world.tbt.converter.UserConverter;
import com.world.tbt.dto.UserDTO;
import com.world.tbt.entity.*;
import com.world.tbt.repository.NewRepository;
import com.world.tbt.repository.RoleRepository;
import com.world.tbt.repository.UserRepository;
import com.world.tbt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private NewRepository newRepository;
	@Autowired
	private UserConverter userConverter;
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public UserEntity rating(Long postid, Long userid, Integer rating) {
		UserEntity userEntity = userRepository.findOneById(userid);
		NewEntity newEntity = newRepository.findOneById(postid);
		userEntity.getRatings().add(PostRating.builder()
				.news(newEntity)
				.user(userEntity)
				.rating(rating)
				.id(PostRatingKey.builder().postId(postid).userId(userid).build())
				.build());
		UserEntity saved;
		try {
			saved = userRepository.save(userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return saved;
	}

	@Override
	public Long getTotalItem()
	{
		return userRepository.count();
	}

	@Override
	public UserDTO findById(long id) 
	{
		UserEntity userEntity = userRepository.findOneById(id);
		return userConverter.toDto(userEntity);
	}

	@Override
	@Transactional
	public UserDTO saveOrUpdate(UserDTO dto) 
	{
		UserEntity userEntity = new UserEntity();
	
		if(dto.getId()!=null) 
		{
			UserEntity oldUser = userRepository.findOneById(dto.getId());
			userEntity = userConverter.toEntity(oldUser,dto);	
		}
		else if(userRepository.existsByUserName(dto.getUserName())==false)//userRepository.findExistByUserName(dto.getUserName())==0
		{
				userEntity = userConverter.toEntity(dto);	
		}
		else
		{
			System.out.println("username exist");
			return null;
		}
		UserEntity savedUserEntity = userRepository.save(userEntity);
		return userConverter.toDto(savedUserEntity);
	}
	
	@Override
	@Transactional
	public void delete(long[] ids) 
	{
		for (long id : ids) 
		{
			if(userRepository.existsById(id)){
				userRepository.deleteById(id);
			}else{
				System.out.println("id:"+id+"not exists");
			}
		}
	}

	@Override
	@Transactional
	public UserDTO changeStatusUser(Optional<Long> id, Optional<Integer> status) {
		Integer[] statusExpected = {0,1};
		if(id.isPresent()&&status.isPresent())
		{
			if(userRepository.existsById(id.get()))
			{
				//Ktra neu status gui len==0or1 thi moi chap nhan
				if(Arrays.stream(statusExpected).anyMatch(integer -> integer==status.get())) {
					UserEntity entity = userRepository.findOneById(id.get());
					entity.setStatus(status.get());
					UserEntity entityUpdated = userRepository.save(entity);
					return userConverter.toDto(entityUpdated);
				}else {
					System.out.println("Status is UnExpected");
					return null;
				}
			}else{
				System.out.println("id not exists");
				return null;
			}
		}
		System.out.println("id OR status not present");
		return null;
	}
	@Transactional
	@Override
	public ResponseEntity<?> changeStatusUserResponseEntity(Optional<Long> id, Optional<Integer> status) {
		Integer[] statusExpected = {0,1};
		if(id.isPresent()&&status.isPresent())
		{
			if(userRepository.existsById(id.get()))
			{
				//Ktra neu status gui len==0or1 thi moi chap nhan
				if(Arrays.stream(statusExpected).anyMatch(integer -> integer==status.get())) {
					UserEntity entity = userRepository.findOneById(id.get());
					entity.setStatus(status.get());
					UserEntity entityUpdated = userRepository.save(entity);
					return ResponseEntity.ok(userConverter.toDto(entityUpdated));
				}else {
					System.out.println("Status out of range");
					ResponseEntity.badRequest().body("Status out of range");
				}
			}else{
				System.out.println("id not exists");
				return ResponseEntity.badRequest().body("id not exists");
			}
		}
		System.out.println("id OR status not present");
		return ResponseEntity.badRequest().body("id OR status not present");
	}

	@Override
	public List<UserDTO> findByUserNameContaining(String username){
		List<UserEntity> userEntities = userRepository.findByUserNameContaining(username);
		List<UserDTO> dtos = userEntities.stream().map(u -> userConverter.toDto(u)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public Page<UserDTO> findByPage(Pageable pageable) {
		Page<UserEntity> entities = userRepository.findAll(pageable);
		Page<UserDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}

	@Override
	public Page<UserDTO> convertPageEntityToPageDTO(Page<UserEntity> entities)
	{
		Page<UserDTO> dtoPage = entities.map(new Function<UserEntity, UserDTO>() {
			@Override
			public UserDTO apply(UserEntity entity) {
				UserDTO dto = new UserDTO();
				dto = userConverter.toDto(entity);
				return dto;
			}
		});

		return dtoPage;
	}

	@Override
	public Page<UserDTO> findByRolesIn(Long roleid, Pageable pageable){
		RoleEntity roleEntity = roleRepository.findOneById(roleid);
		Page<UserEntity> entities = userRepository.findByRolesIn(Arrays.asList(roleEntity), pageable);
		Page<UserDTO> dtoPage = convertPageEntityToPageDTO(entities);
		return dtoPage;
	}

}
