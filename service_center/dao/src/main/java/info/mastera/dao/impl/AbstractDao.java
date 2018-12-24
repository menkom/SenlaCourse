package info.mastera.dao.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.model.base.BaseObject;
import info.mastera.model.base.BaseObject_;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public abstract class AbstractDao<T extends BaseObject> implements IGenericDao<T> {

    private static final Logger logger = Logger.getLogger(AbstractDao.class);
    private static final String OBJECT_UPDATED = "Object with id=%s was successfully updated.";
    private static final String OBJECT_DELETED = "Object with id=%s was successfully deleted.";

    @Autowired
    private SessionFactory sessionFactory;

    protected abstract Class<T> getTClass();

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T create(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public T getById(int id) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getTClass());
        Root<T> root = query.from(getTClass());
        query.select(root).where(builder.equal(root.get(BaseObject_.id), id));
        TypedQuery<T> typedQuery = session.createQuery(query);
        T result = ((Query<T>) typedQuery).uniqueResult();
        return result;
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
        logger.info(String.format(OBJECT_UPDATED, entity.getId()));
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
        logger.info(String.format(OBJECT_DELETED, entity.getId()));
    }

    @Override
    public List<T> getAll() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getTClass());
        Root<T> root = query.from(getTClass());
        query.select(root);
        TypedQuery<T> result = session.createQuery(query);
        return result.getResultList();
    }

    @Override
    public Long count() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<T> root = query.from(getTClass());
        query.select(builder.count(root));
        TypedQuery<Long> result = session.createQuery(query);
        return result.getSingleResult();
    }

}
