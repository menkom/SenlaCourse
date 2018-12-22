package info.mastera.service.impl;

import info.mastera.dao.ICustomerDao;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDao<Customer> customerDao;

    @Override
    public Customer create(Customer entity) {
        return customerDao.create(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerDao.delete(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Long count() {
        return customerDao.count();
    }

}
