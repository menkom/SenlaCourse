package info.mastera.service.impl;

import info.mastera.dao.IGenericDao;
import info.mastera.dao.IUserDao;
import info.mastera.model.User;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<User> implements IUserService<User> {

    @Autowired
    private IUserDao<User> userDao;

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Override
    protected IGenericDao<User> getDao() {
        return userDao;
    }

    public UserService() {
        super();
        logger.info("UserService created.");
    }

    @Override
    public User getByUsername(String userName) {
        User result = userDao.getByUsername(userName);
        logger.info("Looking for user :" + userName + ". Found: " + result);
        return result;
    }

    @Override
    public boolean isCorrectLogin(String userName, String password) {
        return userDao.isCorrectLogin(userName, password);
    }
}
