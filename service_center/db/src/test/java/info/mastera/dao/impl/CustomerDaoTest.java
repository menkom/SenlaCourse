package info.mastera.dao.impl;

import info.mastera.config.TestDataBaseConfig;
import info.mastera.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDataBaseConfig.class)
@Transactional
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Test
    public void testGetTClass() {
        Assert.assertEquals(Customer.class, customerDao.getTClass());
    }

    @Test
    public void testCreate() {
        Customer customer = new Customer();
        customer.setCustomerName("Jane Doe");
        customer.setTelephone("123456");

        customerDao.create(customer);
        Integer id = customer.getId();
        Assert.assertNotNull(id);

        Customer newCustomer = customerDao.getById(id);

        Assert.assertEquals("Jane Doe", newCustomer.getCustomerName());
        Assert.assertEquals("123456", newCustomer.getTelephone());
    }

    @Test
    public void testGetById() {
        Customer customer = customerDao.getById(2);
        Assert.assertEquals("custName", customer.getCustomerName());
        Assert.assertEquals("phoneNum", customer.getTelephone());
    }

    @Test
    public void testUpdate() {
        Customer customer = customerDao.getById(2);
        Assert.assertNotNull(customer);
        customer.setCustomerName("-name-");
        customer.setTelephone("-tel-");
        customerDao.update(customer);
        customer = customerDao.getById(2);
        Assert.assertEquals("-name-", customer.getCustomerName());
        Assert.assertEquals("-tel-", customer.getTelephone());
    }

    @Test
    public void testDelete() {
        long count = customerDao.count();
        List<Customer> customers = customerDao.getAll();
        Customer customer = customers.get(customers.size() - 1);
        customerDao.delete(customer);
        long newCount = customerDao.count();
        Assert.assertEquals(count - 1, newCount);
    }

    @Test
    public void testGetAll() {
        Customer customer = new Customer();
        customer.setCustomerName("Doe Jane");
        customer.setTelephone("123321");
        customerDao.create(customer);

        List<Customer> customers = customerDao.getAll();
        Assert.assertEquals(9, customers.size());
        Customer newCustomer = customers.get(customers.size() - 1);

        Assert.assertEquals("Doe Jane", newCustomer.getCustomerName());
        Assert.assertEquals("123321", newCustomer.getTelephone());
    }

    @Test
    public void testCount() {
        List<Customer> customers = customerDao.getAll();
        Assert.assertEquals(8L, customerDao.count().longValue());
        Assert.assertEquals(8, customers.size());
    }
}