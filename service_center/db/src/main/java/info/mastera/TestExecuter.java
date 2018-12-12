package info.mastera;

import info.mastera.config.HibernateConfig;
import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.service.ICustomerService;
import info.mastera.service.IUserService;
import info.mastera.util.IJwtService;
import info.mastera.util.exceptions.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestExecuter {

    private static final Logger logger = Logger.getLogger(TestExecuter.class);

    private static void testCustomer(ApplicationContext context) {

        ICustomerService<Customer> customerService = context.getBean(ICustomerService.class);

        Customer customer = new Customer();
        customer.setCustomerName("custName");
        customer.setTelephone("phoneNum");

        print(customerService.create(customer));

        List<Customer> customers = customerService.getAll();

        logger.info("Customers: " + customers);

        print(customerService.getById(11));

        customers.forEach(cust -> {
            cust.setCustomerName(cust.getCustomerName() + cust.getId());
            cust.setTelephone(cust.getTelephone() + cust.getId());
            customerService.update(cust);
        });

        customers.forEach(TestExecuter::print);

        if (customerService.getById(1) != null) {
            customerService.delete(customerService.getById(1));
        }
        customers.forEach(TestExecuter::print);


        logger.info("Customer operations passed.");
    }

    private static void testUser(ApplicationContext context) {

        IUserService<User> userService = context.getBean(IUserService.class);

        ICustomerService<Customer> customerService = context.getBean(ICustomerService.class);

        User user = new User();
        user.setUsername("user");
        user.setPassword("7pas");
        user.setUserType(UserType.CUSTOMER);


        user.setCustomer(customerService.getById(7));

//        print(service.create(user));

        List<User> all = userService.getAll();

        logger.info("users: " + user);

        print(userService.getById(3));

//        all.forEach(x -> {
//            x.setUsername(x.getUsername() + x.getId());
//            service.update(x);
//        });

//        all.forEach(TestExecuter::print);

//        if (service.getById(2) != null) {
//            service.delete(service.getById(2));
//        }
        all.forEach(TestExecuter::print);

        logger.info("User operations passed.");
    }

    private static void testJwt(ApplicationContext context) {
        IJwtService jwtService = context.getBean(IJwtService.class);
        String token = jwtService.createToken(1, UserType.ENGINEER);
        logger.info("Token:" + token);

        UserType userType = null;
        try {
            userType = jwtService.getUserGrant(token);
            logger.info("userType:" + userType);
        } catch (AuthenticationException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

//        testCustomer();
//        testUser(context);
        testJwt(context);

        logger.info("Job is done");
    }

    private static void print(Customer customer) {
        if (customer != null) {
            logger.info(customer.getId() + "  |  " + customer.getCustomerName() + "  |  " + customer.getTelephone());
        }
    }

    private static void print(User user) {
        if (user != null) {
            logger.info(user.getId() + "  |  " + user.getUsername()
                    + "  |  " + user.getPassword() + "  |  "
                    + user.getUserType() + "  |  "
                    + (user.getCustomer() != null ? user.getCustomer().getId() : "null"));
        }
    }
}
