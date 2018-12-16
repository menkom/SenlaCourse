package info.mastera.dao.impl;

import info.mastera.config.HibernateConfig;
import info.mastera.config.TestDataBaseConfig;
import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
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
    public void testCreate() {
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

    @Test(expected = PropertyValueException.class)
    public void testCreateEmptyUser() {
        User user = new User();
        userDao.create(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateEmptyUsername() {
        User user = new User();
        user.setPassword("123456");
        user.setUserType(UserType.CUSTOMER);
        userDao.create(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testCreateEmptyPassword() {
        User user = new User();
        user.setUsername("user");
        user.setUserType(UserType.CUSTOMER);
        userDao.create(user);
    }

    @Test(expected = PropertyValueException.class)
    public void testCreateEmptyUserType() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("123456");
        userDao.create(user);
    }

    @Test
    public void testGetById() {
        User user = userDao.getById(1);
        Assert.assertEquals("admin", user.getUsername());
        Assert.assertEquals("admin", user.getPassword());
        Assert.assertNull(user.getCustomer());
        Assert.assertEquals(UserType.ADMIN, user.getUserType());
    }

    @Test
    public void testUpdate() {
        User user = userDao.getById(5);
        Assert.assertNotNull(user);
        user.setUsername("eng5");
        user.setPassword("engineer");
        Customer customer = customerDao.getById(5);
        Assert.assertNotNull(customer);
        user.setCustomer(customer);
        user.setUserType(UserType.RECEIVER);
        userDao.update(user);


        user = userDao.getById(5);
        Assert.assertEquals("eng5", user.getUsername());
        Assert.assertEquals("engineer", user.getPassword());
        Assert.assertEquals(UserType.RECEIVER, user.getUserType());
        Assert.assertEquals(customer.getId(), user.getCustomer().getId());
    }

    @Test
    public void testDelete() {
        long count = userDao.count();
        List<User> users = userDao.getAll();
        User user = users.get(users.size() - 1);
        userDao.delete(user);
        long newCount = userDao.count();
        Assert.assertEquals(count - 1, newCount);
    }

    @Test
    public void testGetAll() {
        User user = new User();
        user.setUsername("-name-");
        user.setPassword("-tel-");
        Customer customer = customerDao.getById(2);
        Assert.assertNotNull(customer);
        user.setCustomer(customer);
        user.setUserType(UserType.RECEIVER);
        userDao.create(user);

        List<User> users = userDao.getAll();
        Assert.assertEquals(18, users.size());
        User newUser = users.get(users.size() - 1);

        Assert.assertEquals("-name-", newUser.getUsername());
        Assert.assertEquals("-tel-", newUser.getPassword());
        Assert.assertEquals(UserType.RECEIVER, user.getUserType());
        Assert.assertEquals(customer.getId(), user.getCustomer().getId());
    }

    @Test
    public void testCount() {
        List<User> users = userDao.getAll();
        Assert.assertEquals(17L, userDao.count().longValue());
        Assert.assertEquals(17, users.size());
    }

    @Test
    public void testGetByUsername() {
        User user = userDao.getByUsername("admin");
        Assert.assertNotNull(user);
        Assert.assertEquals(1, user.getId().intValue());
        Assert.assertEquals("admin", user.getPassword());
        Assert.assertEquals(UserType.ADMIN, user.getUserType());
    }

}