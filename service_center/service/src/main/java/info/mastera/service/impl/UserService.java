package info.mastera.service.impl;

import info.mastera.dao.IUserDao;
import info.mastera.model.User;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao<User> userDao;

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Override
    public User create(User entity) {
        return userDao.create(entity);
    }

    @Override
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getByIdWithCustomer(int id) {
        return userDao.getByIdWithCustomer(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public List<User> getAllAndCustomer() {
        return userDao.getAllAndCustomer();
    }

    @Override
    public Long count() {
        return userDao.count();
    }

    @Override
    public User getByUsername(String userName) {
        User result = userDao.getByUsername(userName);
        logger.info("Looking for user :" + userName + ". Found: " + result);
        return result;
    }

}
