package info.mastera.dao.impl;

import info.mastera.dao.IUserDao;
import info.mastera.model.User;
import info.mastera.model.User_;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao<User> {
    @Override
    protected Class<User> getTClass() {
        return User.class;
    }

    @Override
    public User getByUsername(String userName) {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(builder.equal(root.get(User_.username), userName));
        TypedQuery<User> result = session.createQuery(query);
        User user = ((Query<User>) result).uniqueResult();
        return user;
    }

    @Override
    public List<User> getAllAndCustomer() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(getTClass());
        Root<User> root = query.from(getTClass());
        root.fetch(User_.customer, JoinType.LEFT);
        query.select(root);
        TypedQuery<User> result = session.createQuery(query);
        return result.getResultList();
    }

}
