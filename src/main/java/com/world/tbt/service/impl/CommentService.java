package com.world.tbt.service.impl;

import com.world.tbt.converter.CommentConverter;
import com.world.tbt.dto.AppUser;
import com.world.tbt.dto.CommentDTO;
import com.world.tbt.entity.CommentEntity;
import com.world.tbt.entity.NewEntity;
import com.world.tbt.entity.UserEntity;
import com.world.tbt.repository.CommentRepository;
import com.world.tbt.repository.NewRepository;
import com.world.tbt.repository.UserRepository;
import com.world.tbt.service.ICommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CommentService implements ICommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    NewRepository newRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public CommentDTO saveOrUpdate(CommentDTO commentDTO) {
        CommentEntity converted = CommentConverter.toEntity(commentDTO);
        NewEntity newEntity = null;
        //if postid is null return null badrequest without postid error
        if(commentDTO.getIdPost()!=null){
            newEntity= newRepository.findOneNewById_Custom(commentDTO.getIdPost());
        }else return null;
        //if postid from client not null but is a super big number like 99999 will return null newsEntity
        if(newEntity==null)
        {
            return null;
        }
        converted.setPost(newEntity);

        Long userid=0L;
        UserEntity userEntity;
        if(commentDTO.getUserid()==null||commentDTO.getUserid()==0L)
        {
            AppUser appUser = (AppUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
            userid=appUser.getId();
        }
        userid=commentDTO.getUserid();
        userEntity = userRepository.findOneById(userid);
        //if userid from client not null but is a super big number like 99999 will return null userEntity
        if(userEntity==null){
            return null;
        }
        converted.setUser(userEntity);

        CommentEntity commentEntitySaved = commentRepository.save(converted);
        return CommentConverter.toDto(commentEntitySaved);
    }

    @Override
    public Page<CommentDTO> findAllCommentsOfAPost(Long id, Pageable pageable) {
        Page<CommentEntity> comments = commentRepository.findAllByPost_Id(id, pageable);
        Page<CommentDTO> commentDTOS = convertPageEntityToPageDTO(comments);
        return commentDTOS;
    }
    @Override
    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteACommentById(Long id) {
        if(id!=null && id!=0L && commentRepository.findById(id).isPresent())
        {
            commentRepository.deleteById(id);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id not found");
        }
        return ResponseEntity.ok("Delete successed");
    }

    @Override
    public CommentDTO findById(Long id) {
        CommentEntity entity = null;
        try {
            entity = commentRepository.findById(id).orElseThrow(()->new NotFoundException("id comment not found"));
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        CommentDTO dto = new CommentDTO();
        if(entity!=null)
        {
            dto.setIdPost(entity.getId());
            dto.setCreatedBy(entity.getCreatedBy());
            dto.setModifiedBy(entity.getModifiedBy());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setModifiedDate(entity.getModifiedDate());
            dto.setContent(entity.getContent());
        }
        return dto;
    }

    @Override
    public List<CommentDTO> findByContentContaining(String keyword) {
        List<CommentEntity> comments = commentRepository.findByContentContaining(keyword);
        List<CommentDTO> dtos = comments.stream().map(c -> CommentConverter.toDto(c)).collect(Collectors.toList());
        return dtos;
    }
    @Override
    public Page<CommentDTO> findByPage(Pageable pageable) {
        Page<CommentEntity> entities = commentRepository.findAll(pageable);
        Page<CommentDTO> dtoPage = convertPageEntityToPageDTO(entities);
        return dtoPage;
    }

    @Override
    public Page<CommentDTO> convertPageEntityToPageDTO(Page<CommentEntity> entities)
    {
        Page<CommentDTO> dtoPage = entities.map(new Function<CommentEntity, CommentDTO>() {
            @Override
            public CommentDTO apply(CommentEntity entity) {
                CommentDTO dto = new CommentDTO();
                dto = CommentConverter.toDto(entity);
                return dto;
            }
        });

        return dtoPage;
    }
}
