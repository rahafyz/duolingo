package com.mapsa.duolingo.common;


import java.util.List;

public interface GenericModelMapper<T extends BaseEntity,D> {
    public T toEntity(D dto);
    public D toDto(T entity);

    public List<T> toListEntity(List<D> dto);
    public List<D> toListDto(List<T> entity);
}
