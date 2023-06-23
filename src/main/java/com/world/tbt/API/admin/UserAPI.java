package com.world.tbt.API.admin;

import com.world.tbt.dto.AppUser;
import com.world.tbt.dto.DataTable;
import com.world.tbt.dto.UserDTO;
import com.world.tbt.entity.UserEntity;
import com.world.tbt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Optional;

@RestController(value ="userAPIOfAdmin")
@RequestMapping("api/user")
public class UserAPI {
	@Autowired
	private IUserService userService;
	@GetMapping
	public ResponseEntity<?> findAll(@RequestParam Integer draw, @RequestParam Integer start, @RequestParam Optional<Integer> length, @RequestParam(name = "orderCol") Optional<String> sort, @RequestParam(name = "sortDir") Optional<String> direction, @RequestParam Optional<String> search, @RequestParam Optional<Long> roleid)
	{
		if(search.isPresent()&&search!=null&&!search.get().equals(""))
		{
			return ResponseEntity.ok(new DataTable(draw, 0,0, userService.findByUserNameContaining(search.get())));

		}
		Integer page = start/ length.orElse(3);
		Pageable pageable = PageRequest.of(page
										, length.orElse(3)
										, Sort.by(Sort.Direction.fromString(direction.orElse("DESC"))
										, sort.orElse("id")));
		Page<UserDTO> dataPerPage;
		DataTable returnClient;
		//khi co roleid tuc la loc nguoi dung theo role con khong co roleid tuc la hien thi toan bo
		if(roleid.isPresent()){
			dataPerPage = userService.findByRolesIn(roleid.orElse(1L), pageable);
			returnClient = new DataTable(draw, (int)(dataPerPage.getTotalElements()), (int)(dataPerPage.getTotalElements()), dataPerPage.getContent());
		}else{
			dataPerPage = userService.findByPage(pageable);
			returnClient = new DataTable(draw, (int)(dataPerPage.getTotalElements()), (int)(dataPerPage.getTotalElements()), dataPerPage.getContent());
		}
		return ResponseEntity.ok(returnClient);
	}
	@PostMapping("/rating")
	@PreAuthorize("isAuthenticated()")
	public UserEntity ratingApi(@NotNull  Long postid, Long userid, @NotNull Integer rating, Authentication authentication){
		if(authentication!=null){
			AppUser appUser =(AppUser)authentication.getPrincipal();
			userid=appUser.getId();
		}
		UserEntity userEntity = userService.rating(postid,userid,rating);
		return userEntity;
	}
	@PostMapping
	public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO, Authentication authentication)
	{
		if(authentication!=null)
		{
			Long isAdmin = authentication.getAuthorities().stream().filter(a->a.getAuthority().equalsIgnoreCase("ROLE_ADMIN")).count();
			if(isAdmin==0L){
			userDTO.setRoleCode(Arrays.asList("ROLE_USER"));
			}
		}else{
			userDTO.setRoleCode(Arrays.asList("ROLE_USER"));
		}
		//chi co admin moi set duoc status cua user khi tao con lai khi tao deu mac dinh role=ROLE_USER
		userDTO.setStatus(1);
		UserDTO saved = userService.saveOrUpdate(userDTO);
		if(saved==null)
		{
			return ResponseEntity.badRequest().body("username exist");
		}
		return ResponseEntity.ok(saved);
	}
	@DeleteMapping
	@PreAuthorize("isAuthenticated()")
	public void delete(@RequestBody long[] ids)
	{
		userService.delete(ids);
	}
	
	@PutMapping
	@PreAuthorize("isAuthenticated()")
	public UserDTO updateUser(@RequestBody UserDTO userDTO) 
	{
		userDTO.setStatus(1);
		return userService.saveOrUpdate(userDTO);
	}

	@PutMapping("/status")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> changeStatusUser(@RequestBody UserDTO userDTO)
	{
		if(userDTO!=null){
			if(userDTO.getId()!=null && userDTO.getStatus()!=null)
			{
				UserDTO dto = userService.changeStatusUser(Optional.of(userDTO.getId()), Optional.of(userDTO.getStatus()));
				return dto!=null?ResponseEntity.ok(dto):ResponseEntity.badRequest().build();
			}
		}
		return ResponseEntity.badRequest().body("user is null");
	}
	@PutMapping("/status/re")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> changeStatusUserResponseEntitService(@RequestBody UserDTO userDTO)
	{
		if(userDTO!=null){
			if(userDTO.getId()!=null && userDTO.getStatus()!=null)
			{
				return userService.changeStatusUserResponseEntity(Optional.of(userDTO.getId()), Optional.of(userDTO.getStatus()));
			}else {
				return ResponseEntity.badRequest().body("id or status is null");
			}
		}
		return ResponseEntity.badRequest().body("user is null");
	}
}
