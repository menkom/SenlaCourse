package info.mastera.dao;

import info.mastera.model.UserLoginHistory;

import java.util.List;

public interface IUserLoginHistoryDao<T extends UserLoginHistory> extends IGenericDao<T> {

    List<T> getAllAndUser();

}
