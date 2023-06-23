package com.world.tbt.service;

import com.world.tbt.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService
{
	void delete(long[] ids);

	CategoryDTO saveOrUpdate(CategoryDTO dto);

	CategoryDTO findByCode(String code);

	CategoryDTO findById(long id);

	List<CategoryDTO> findAll();

	List<CategoryDTO> findByNameContaining(String nameCategory);

	Page<CategoryDTO> findByPage(Pageable pageable);
}


