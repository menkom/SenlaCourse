package info.mastera.service;

import info.mastera.model.User;

public interface IUserService<T extends User> extends IGenericService<T> {

    User getByUsername(String userName);

    boolean isCorrectLogin(String userName, String password);

}
