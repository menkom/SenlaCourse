package info.mastera.dao;

import info.mastera.model.User;

public interface IUserDao<T extends User> extends IGenericDao<T> {

    User getByUsername(String userName);

    boolean isCorrectLogin(String userName, String password);

}
