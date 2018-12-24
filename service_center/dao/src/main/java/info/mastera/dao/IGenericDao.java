package info.mastera.dao;

import info.mastera.model.base.BaseObject;

import javax.persistence.criteria.Root;
import java.util.List;

public interface IGenericDao<T extends BaseObject> {

    /**
     * Create
     *
     * @param entity Save object to DB
     * @return T
     */
    T create(T entity);

    /**
     * Read
     *
     * @param id Get T object with id
     * @return T
     */
    T getById(int id);

    /**
     * Update
     *
     * @param entity Entity to update
     */
    void update(T entity);

    /**
     * Delete
     *
     * @param entity Entity to delete
     */
    void delete(T entity);

    /**
     * getAll
     *
     * @return List<T> all T objects
     */
    List<T> getAll();

    /**
     * count()
     *
     * @return Long
     */
    Long count();

}