package info.mastera.dao;

import info.mastera.model.User;

import java.util.List;

public interface IUserDao<T extends User> extends IGenericDao<T> {

    User getByUsername(String userName);

    User getByIdWithCustomer(Integer id);

    List<T> getAllAndCustomer();

}
