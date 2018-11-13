package info.mastera.controller;

import java.util.List;

import info.mastera.model.Customer;

public interface IAppController {

	List<Customer> getAllCustomers();

	Customer createCustomer(Customer customer);
}
