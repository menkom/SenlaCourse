package info.mastera.service;

import info.mastera.model.Customer;

import java.util.List;

public interface ICustomerService{

    Customer create(Customer entity);

    void delete(Customer entity);

    void update(Customer entity);

    Customer getById(int id);

    List<Customer> getAll();

    Long count();

}
