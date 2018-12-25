package info.mastera.service;

import info.mastera.model.User;

import java.util.List;

public interface IUserService {

    User create(User entity);

    void delete(User entity);

    void update(User entity);

    User getById(int id);

    User getByIdWithCustomer(int id);

    List<User> getAll();

    List<User> getAllAndCustomer();

    Long count();

    User getByUsername(String userName);

}
