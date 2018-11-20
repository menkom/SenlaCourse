package info.mastera.dao;

import org.springframework.stereotype.Component;

import info.mastera.model.Customer;

@Component
public interface ICustomerDao<T extends Customer> extends IGenericDao<T> {

}
