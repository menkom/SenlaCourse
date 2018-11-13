package info.mastera.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.mastera.dao.ICustomerDao;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerDao<Customer> customerDao;

    @Override
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Override
    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    @Override
    public Customer create(Customer entity) {
        return customerDao.create(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDao.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerDao.delete(entity);
    }

}
