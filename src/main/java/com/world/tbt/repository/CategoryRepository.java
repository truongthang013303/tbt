package com.world.tbt.repository;

import com.world.tbt.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>
{
	CategoryEntity findOneByCode(String code);
	CategoryEntity findOneById(Long id);
	boolean existsByCode(String code);
	List<CategoryEntity> findByNameContaining(String nameCategory);
}
