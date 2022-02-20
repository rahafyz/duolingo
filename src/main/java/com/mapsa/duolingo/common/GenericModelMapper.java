package com.mapsa.duolingo.common;


import org.mapstruct.Mapper;

public interface GenericModelMapper<T extends BaseEntity,D> {
    public T toEntity(D dto);
    public D toDto(T entity);
}
