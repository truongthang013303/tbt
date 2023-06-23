package com.world.tbt.service;

import com.world.tbt.dto.NewDTO;
import com.world.tbt.dto.RatingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INewService {
	List<NewDTO> findByTitleContainingAndStatus(String searchWord);
	List<NewDTO> findByTitleContaining(String searchWord);
	Page<NewDTO> findAllByCreatedBy(String createdBy, Pageable pageable);
	Page<NewDTO> findByPage(Pageable pageable);
	Page<NewDTO> findByPage(Integer status, Pageable pageable);
	void delete(long[] ids);
	NewDTO saveOrUpdate(NewDTO dto) throws Exception;
	Page<NewDTO> findAllByCategoryCodePage(String cateCode, Integer status, Pageable pageable);
	NewDTO findById(long id);
	NewDTO findByIdAndStatus(long id, int status);
	Long getTotalItem();
	ResponseEntity<?> rating(RatingDTO ratingDTO);
	List<NewDTO> findByTitleContainingAndCreatedBy(String search, String createdBy);
	NewDTO publishPost(NewDTO dto);
	Long countByStatus(Integer status);
	boolean existsById(Long id);
	Page<NewDTO> findAllByCategoryId(Long categoryid, Pageable pageable);
}
