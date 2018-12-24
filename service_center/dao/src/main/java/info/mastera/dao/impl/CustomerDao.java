package info.mastera.dao.impl;

import info.mastera.dao.ICustomerDao;
import info.mastera.model.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Root;

@Repository
public class CustomerDao extends AbstractDao<Customer> implements ICustomerDao<Customer> {

    @Override
    protected Class<Customer> getTClass() {
        return Customer.class;
    }

}
