package com.mapsa.duolingo.common;


import com.mapsa.duolingo.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericService<T, U> implements IGenericService<T, U> {

    protected abstract GenericRepository<T, U> getRepository();

    private String getEntity() {
        return ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0].getTypeName();
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    //add exceptions and get subclass
    @Override
    public T getById(U id) {
        return getRepository().findById(id).orElseThrow(
                () -> new CustomException("There is no " + getEntity() + " by this id", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<T> getAll() {
        return (List<T>) getRepository().findAll();
    }

    @Override
    public void delete(U id) {
        getById(id);
        getRepository().deleteById(id);
    }

    @Override
    public void update(U id, T entity) {
        T update = getById(id);
        getRepository().save(update);
    }
}
