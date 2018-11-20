package info.mastera.dao.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.model.base.BaseObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public abstract class AbstractDao<T extends BaseObject> implements IGenericDao<T> {

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
        return (T) getSession().find(getTClass(), id);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
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
