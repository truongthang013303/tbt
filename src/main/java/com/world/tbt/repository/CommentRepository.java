package com.world.tbt.repository;

import com.world.tbt.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    Page<CommentEntity> findAllByPost_Id(Long id, Pageable pageable);

    @Modifying
    @Query("delete from CommentEntity c where c.id in ?1")
    void deleteCommentsWithIds(List<Integer> ids);

    List<CommentEntity> findByContentContaining(String keyword);
}
