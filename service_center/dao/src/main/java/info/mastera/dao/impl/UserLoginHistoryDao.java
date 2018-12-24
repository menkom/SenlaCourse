package info.mastera.dao.impl;

import info.mastera.dao.IUserLoginHistoryDao;
import info.mastera.model.UserLoginHistory;
import info.mastera.model.UserLoginHistory_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserLoginHistoryDao extends AbstractDao<UserLoginHistory> implements IUserLoginHistoryDao<UserLoginHistory> {
    @Override
    protected Class<UserLoginHistory> getTClass() {
        return UserLoginHistory.class;
    }

    @Override
    public List<UserLoginHistory> getAllAndUser() {
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserLoginHistory> query = builder.createQuery(getTClass());
        Root<UserLoginHistory> root = query.from(getTClass());
        root.fetch(UserLoginHistory_.user, JoinType.LEFT);
        query.select(root);
        TypedQuery<UserLoginHistory> result = session.createQuery(query);
        return result.getResultList();
    }
}
