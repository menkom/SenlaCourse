package info.mastera.service.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.dao.IUserLoginHistoryDao;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IUserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserLoginHistoryService implements IUserLoginHistoryService {

    @Autowired
    IUserLoginHistoryDao<UserLoginHistory> userLoginHistoryDao;

    @Override
    public UserLoginHistory create(UserLoginHistory entity) {
        return userLoginHistoryDao.create(entity);
    }

    @Override
    public void delete(UserLoginHistory entity) {
        userLoginHistoryDao.delete(entity);
    }

    @Override
    public void update(UserLoginHistory entity) {
        userLoginHistoryDao.update(entity);
    }

    @Override
    public UserLoginHistory getById(int id) {
        return userLoginHistoryDao.getById(id);
    }

    @Override
    public List<UserLoginHistory> getAll() {
        return userLoginHistoryDao.getAll();
    }

    @Override
    public List<UserLoginHistory> getAllAndUser() {
        return userLoginHistoryDao.getAllAndUser();
    }

    @Override
    public Long count() {
        return userLoginHistoryDao.count();
    }

}
