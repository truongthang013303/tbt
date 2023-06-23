package com.world.tbt.service;

import com.world.tbt.dto.PrivilegeDTO;
import com.world.tbt.entity.PrivilegeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPrivilegeService {
    public List<PrivilegeDTO> findAll();
    public PrivilegeEntity findOneByCode(String code);

    public PrivilegeDTO findOneById(Long id);
    public PrivilegeDTO saveOrUpdate(PrivilegeDTO dto);
    void delete(long[] ids);

    List<PrivilegeDTO> findByNameContaining(String name);

    Page<PrivilegeDTO> findByPage(Pageable pageable);

    Page<PrivilegeDTO> convertPageEntityToPageDTO(Page<PrivilegeEntity> entities);
}
