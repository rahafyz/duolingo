package com.mapsa.duolingo.common;

import java.util.List;

public interface IGenericService<T extends BaseEntity,U>{
    public T save(T entity);
    public T getById(U id);
    public List<T> getAll();
    public void delete(U id);
    public void update(U id,T entity);
}
