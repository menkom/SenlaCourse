package info.mastera.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import info.mastera.dao.IGenericDao;
import info.mastera.model.base.BaseObject;

@Repository
public abstract class AbstractDao<T extends BaseObject> implements IGenericDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected abstract Class<T> getTClass();

    protected Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    @Override
    public T create(T t) {
        getSession().save(t);
        return t;
    }

    @Override
    public T getById(int id) {
        return (T) getSession().find(getTClass(), id);
    }

    @Override
    public void update(T entity) {
        getSession().merge(entity);
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

}
