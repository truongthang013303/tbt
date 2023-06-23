package com.world.tbt.converter;
public interface Converter<entityType, dtoType>{
    public dtoType toDto(entityType entity);
    public entityType toEntity(dtoType dto);
    public entityType toEntity(entityType entity, dtoType dto);
}
