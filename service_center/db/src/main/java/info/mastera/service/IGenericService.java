package info.mastera.service;

import info.mastera.model.base.BaseObject;

import java.util.List;

public interface IGenericService<T extends BaseObject> {

    T create(T entity);

    void delete(T entity);

    void update(T entity);

    T getById(int id);

    List<T> getAll();

    Long count();

}
