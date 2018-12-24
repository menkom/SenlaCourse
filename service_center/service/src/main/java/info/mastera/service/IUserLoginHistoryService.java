package info.mastera.service;

import info.mastera.model.UserLoginHistory;

import java.util.List;

public interface IUserLoginHistoryService{

    UserLoginHistory create(UserLoginHistory entity);

    void delete(UserLoginHistory entity);

    void update(UserLoginHistory entity);

    UserLoginHistory getById(int id);

    List<UserLoginHistory> getAll();

    List<UserLoginHistory> getAllAndUser();

    Long count();

}
