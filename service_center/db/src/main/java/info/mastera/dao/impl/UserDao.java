package info.mastera.dao.impl;

import info.mastera.dao.IUserDao;
import info.mastera.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao<User> {
    @Override
    protected Class<User> getTClass() {
        return User.class;
    }
}
