package info.mastera.dao.impl;

import info.mastera.dao.IUserLoginHistoryDao;
import info.mastera.model.UserLoginHistory;
import info.mastera.model.UserLoginHistory_;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class UserLoginHistoryDao extends AbstractDao<UserLoginHistory> implements IUserLoginHistoryDao<UserLoginHistory> {
    @Override
    protected Class<UserLoginHistory> getTClass() {
        return UserLoginHistory.class;
    }

    @Override
    public void fetchLazyObjects(Root<UserLoginHistory> root) {
        root.fetch(UserLoginHistory_.user, JoinType.LEFT);
    }

}
