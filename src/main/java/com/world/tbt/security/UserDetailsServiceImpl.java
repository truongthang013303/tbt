package com.world.tbt.security;

import java.util.*;

import com.world.tbt.dto.AppUser;
import com.world.tbt.entity.PrivilegeEntity;
import com.world.tbt.entity.RoleEntity;
import com.world.tbt.entity.UserEntity;
import com.world.tbt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, 1);
		if(userEntity==null)
		{
			throw new UsernameNotFoundException("cloud no find user");
		}
		Set<PrivilegeEntity> priviInEveryRole = new HashSet<>();
		for(RoleEntity i:userEntity.getRoles())
		{
			priviInEveryRole.addAll(i.getPrivileges());
		}
		AppUser myUser = new AppUser(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword(),true, true, true, true, getAuthorities(userEntity.getRoles()), userEntity.getRoles(), priviInEveryRole, userEntity.getInterest());
		return myUser;
	}
	private Collection<? extends GrantedAuthority> getAuthorities(
			Collection<RoleEntity> roles) {

		return getGrantedAuthorities(getPrivileges(roles));
	}

	//Get all Privileges and Roles, add to List<String> privileges
	private List<String> getPrivileges(Collection<RoleEntity> roles) {

		List<String> privileges = new ArrayList<>();
		List<PrivilegeEntity> collection = new ArrayList<>();
		for (RoleEntity role : roles) {
			privileges.add(role.getCode());
			collection.addAll(role.getPrivileges());
		}
		for (PrivilegeEntity item : collection) {
			privileges.add(item.getCode());
		}
		return privileges;
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
