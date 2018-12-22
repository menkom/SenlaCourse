package info.mastera.service.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.dao.IUserLoginHistoryDao;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IUserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginHistoryService extends AbstractService<UserLoginHistory>
        implements IUserLoginHistoryService<UserLoginHistory> {

    @Autowired
    IUserLoginHistoryDao userLoginHistoryDao;

    @Override
    protected IGenericDao<UserLoginHistory> getDao() {
        return userLoginHistoryDao;
    }
}
