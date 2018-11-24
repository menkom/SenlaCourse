package info.mastera.service.impl;

import info.mastera.dao.ICustomerDao;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService extends AbstractService<Customer> implements ICustomerService {

    @Autowired
    private ICustomerDao<Customer> customerDao;

    private static final Logger logger = Logger.getLogger(CustomerService.class);

    @Override
    protected ICustomerDao<Customer> getDao() {
        return customerDao;
    }

    public CustomerService() {
        super();
        logger.info("CustomerService created.");
    }
}
