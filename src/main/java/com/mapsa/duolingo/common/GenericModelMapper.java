package com.mapsa.duolingo.common;


import java.util.List;

public interface GenericModelMapper<T,D> {
    T toEntity(D dto);
    D toDto(T entity);

    List<T> toListEntity(List<D> dto);
    List<D> toListDto(List<T> entity);
}
