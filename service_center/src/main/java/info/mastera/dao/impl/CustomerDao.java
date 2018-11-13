package info.mastera.dao.impl;

import org.springframework.stereotype.Repository;

import info.mastera.dao.ICustomerDao;
import info.mastera.model.Customer;

@Repository
public class CustomerDao extends AbstractDao<Customer> implements ICustomerDao<Customer> {

	@Override
	protected Class<Customer> getTClass() {
		return Customer.class;
	}

}
