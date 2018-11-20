package info.mastera.service.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.model.base.BaseObject;
import info.mastera.service.IGenericService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractService<T extends BaseObject> implements IGenericService<T> {

    private static final Logger logger = Logger.getLogger(AbstractService.class);
    private static final String OBJECT_UPDATED = "Object with id=%s was successfully updated.";
    private static final String OBJECT_DELETED = "Object with id=%s was successfully deleted.";

    protected abstract IGenericDao<T> getDao();

    @Override
    public T create(T entity) {
        return getDao().create(entity);
    }

    @Override
    public void delete(T entity) {
        getDao().delete(entity);
        logger.info(String.format(OBJECT_DELETED, entity.getId()));
    }

    @Override
    public void update(T entity) {
        getDao().update(entity);
        logger.info(String.format(OBJECT_UPDATED, entity.getId()));
    }

    @Override
    public T getById(int id) {
        return getDao().getById(id);
    }

    @Override
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Override
    public Long count() {
        return getDao().count();
    }

}
