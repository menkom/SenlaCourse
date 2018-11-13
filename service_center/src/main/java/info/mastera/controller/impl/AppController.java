package info.mastera.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import info.mastera.controller.IAppController;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;

@Controller
public class AppController implements IAppController {

	@Autowired
	ICustomerService customerService;

	public List<Customer> getAllCustomers() {
		return customerService.getAll();
	}

	public Customer createCustomer(Customer customer) {
		return customerService.create(customer);
	}

}
