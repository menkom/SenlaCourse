package info.mastera.dao.impl;

import info.mastera.dao.IUserLoginHistoryDao;
import info.mastera.model.UserLoginHistory;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginHistoryDao extends AbstractDao<UserLoginHistory> implements IUserLoginHistoryDao<UserLoginHistory> {
    @Override
    protected Class<UserLoginHistory> getTClass() {
        return UserLoginHistory.class;
    }
}
