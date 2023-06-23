package com.world.tbt.converter;
import com.world.tbt.dto.CommentDTO;
import com.world.tbt.entity.CommentEntity;
public class CommentConverter{
    public static CommentEntity toEntity(CommentDTO dto) {
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        return entity;
    }
    public static CommentDTO toDto(CommentEntity entity) {
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setIdPost(entity.getPost().getId());
        dto.setUserid(entity.getUser().getId());
        dto.setTitlePost(entity.getPost().getTitle());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setCreatedBy(entity.getModifiedBy());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }
}
