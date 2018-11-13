package info.mastera.service;

import java.util.List;

import org.springframework.stereotype.Component;

import info.mastera.model.Customer;

@Component
public interface ICustomerService {

	Customer getById(int id);

	List<Customer> getAll();

	Customer create(Customer t);

	void update(Customer entity);

	void delete(Customer entity);

}
