package com.world.tbt.repository;
import com.world.tbt.entity.RoleEntity;
import com.world.tbt.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Collection;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long>
{
	UserEntity findOneByUserNameAndStatus(String name, int status);
	UserEntity findOneById(long id);		
	boolean existsByUserName(String userName);
	List<UserEntity> findByUserNameContaining(String username);
	@Query( "select u from UserEntity u  where u.roles in :roles" )
	public List<UserEntity> findByRoles(@Param("roles") List<String> roles);
	Page<UserEntity> findByRolesIn(Collection<RoleEntity> roles, Pageable pageable);
}
