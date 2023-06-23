package com.world.tbt.service;

import com.world.tbt.dto.CommentDTO;
import com.world.tbt.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ICommentService {
    CommentDTO saveOrUpdate(CommentDTO commentDTO);

    Page<CommentDTO> findAllCommentsOfAPost(Long id, Pageable pageable);

    Page<CommentDTO> findByPage(Pageable pageable);
    List<CommentDTO> findByContentContaining(String keyword);
    ResponseEntity<?> deleteACommentById(@RequestBody Long id);

    CommentDTO findById(Long id);

    Page<CommentDTO> convertPageEntityToPageDTO(Page<CommentEntity> entities);
}
