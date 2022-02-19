package com.mapsa.duolingo.common;


public interface GenericModelMapper<T extends BaseEntity,D> {
    public T toEntity(D dto);
    public D toDTO(T entity);
}
