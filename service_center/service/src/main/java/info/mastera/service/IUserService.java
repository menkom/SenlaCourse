package info.mastera.service;

import info.mastera.model.User;

import java.util.List;

public interface IUserService {

    User create(User entity);

    void delete(User entity);

    void update(User entity);

    User getById(int id);

    List<User> getAll();

    Long count();

    User getByUsername(String userName);

    boolean isCorrectLogin(String userName, String password);

}
