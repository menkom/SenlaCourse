package info.mastera.dao.impl;

import info.mastera.config.HibernateConfig;
import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@Transactional
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Autowired
    CustomerDao customerDao;

    @Test
    public void testGetTClass() {
        Assert.assertEquals(User.class, userDao.getTClass());
    }

    @Test
    public void create() {
        User user = new User();
        user.setUsername("Jane Doe");
        user.setPassword("123456");
        Customer customer = customerDao.getById(2);
        Assert.assertNotNull(customer);
        user.setCustomer(customer);
        user.setUserType(UserType.CUSTOMER);

        userDao.create(user);

        Integer id = user.getId();
        Assert.assertNotNull(id);

        User newUser = userDao.getById(id);
        Assert.assertNotNull(newUser);

        Assert.assertEquals("Jane Doe", newUser.getUsername());
        Assert.assertEquals("123456", newUser.getPassword());
        Assert.assertNotNull(newUser.getCustomer());
        Assert.assertEquals(newUser.getCustomer().getId().intValue(), 2);
        Assert.assertEquals(UserType.CUSTOMER, newUser.getUserType());

    }

    @Test
    public void getById() {
        User user = userDao.getById(6);
        Assert.assertEquals("user", user.getUsername());
        Assert.assertEquals("7pas", user.getPassword());
        Assert.assertNull(user.getCustomer());
        Assert.assertEquals(UserType.CUSTOMER, user.getUserType());
    }

    @Test
    public void update() {
        User user = userDao.getById(6);
        Assert.assertNotNull(user);
        user.setUsername("-name-");
        user.setPassword("-tel-");
        Customer customer = customerDao.getById(2);
        Assert.assertNotNull(customer);
        user.setCustomer(customer);
        user.setUserType(UserType.RECEIVER);
        userDao.update(user);


        user = userDao.getById(6);
        Assert.assertEquals("-name-", user.getUsername());
        Assert.assertEquals("-tel-", user.getPassword());
        Assert.assertEquals(UserType.RECEIVER, user.getUserType());
        Assert.assertEquals(customer.getId(), user.getCustomer().getId());
    }

    @Test
    public void delete() {
        long count = userDao.count();
        List<User> users = userDao.getAll();
        User user = users.get(users.size() - 1);
        userDao.delete(user);
        long newCount = userDao.count();
        Assert.assertEquals(count - 1, newCount);
    }

    @Test
    public void getAll() {
        User user = new User();
        user.setUsername("-name-");
        user.setPassword("-tel-");
        Customer customer = customerDao.getById(2);
        Assert.assertNotNull(customer);
        user.setCustomer(customer);
        user.setUserType(UserType.RECEIVER);
        userDao.create(user);

        List<User> users = userDao.getAll();
        Assert.assertEquals(6, users.size());
        User newUser = users.get(users.size() - 1);

        Assert.assertEquals("-name-", newUser.getUsername());
        Assert.assertEquals("-tel-", newUser.getPassword());
        Assert.assertEquals(UserType.RECEIVER, user.getUserType());
        Assert.assertEquals(customer.getId(), user.getCustomer().getId());
    }

    @Test
    public void count() {
        List<User> users = userDao.getAll();
        Assert.assertEquals(5L, userDao.count().longValue());
        Assert.assertEquals(5, users.size());
    }
}