package com.mapsa.duolingo.common;


import com.mapsa.duolingo.exception.NotFoundException;

import java.lang.reflect.ParameterizedType;
import java.util.List;


public class GenericService<T, U> implements IGenericService<T, U> {
    private GenericRepository<T, U> repository;

    public GenericService(GenericRepository<T, U> repository) {
        this.repository = repository;
    }

    private String getEntity() {
        return ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0].getTypeName();
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }

    //add exceptions and get subclass
    @Override
    public T getById(U id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("There is no " + getEntity() + " by this id"));
    }

    @Override
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public void delete(U id) {
        getById(id);
        repository.deleteById(id);
    }

    @Override
    public void update(U id, T entity) {
        T update = getById(id);
        repository.save(update);
    }
}
