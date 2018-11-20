package info.mastera.dao;

import java.util.List;

import info.mastera.model.base.BaseObject;

public interface IGenericDao<T extends BaseObject> {

    /**
     * Create
     *
     * @param entity
     * @return T
     */
    T create(T entity);

    /**
     * Read
     *
     * @param id
     * @return T
     */
    T getById(int id);

    /**
     * Update
     *
     * @param entity
     */
    void update(T entity);

    /**
     * Delete
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * getAll
     *
     * @return List<T>
     */
    List<T> getAll();

    /**
     * count()
     *
     * @return Long
     */
    Long count();

}