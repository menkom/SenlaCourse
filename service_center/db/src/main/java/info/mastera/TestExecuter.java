package info.mastera;

import info.mastera.config.HibernateConfig;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestExecuter {

    private static final Logger logger = Logger.getLogger(TestExecuter.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        ICustomerService customerService = context.getBean(ICustomerService.class);

        Customer customer = new Customer();
        customer.setCustomerName("custName");
        customer.setTelephone("phoneNum");

        printCustomer(customerService.create(customer));

        List<Customer> customers = customerService.getAll();

        logger.info("Customers: " + customers);

//        printCustomer(customerService.getById(11));

//        customers.forEach(cust -> {
//            cust.setCustomerName(cust.getCustomerName() + cust.getId());
//            cust.setTelephone(cust.getTelephone() + cust.getId());
//            customerService.update(cust);
//        });

        customers.forEach(TestExecuter::printCustomer);

        if (customerService.getById(1) != null) {
            customerService.delete(customerService.getById(1));
        }
        customers.forEach(TestExecuter::printCustomer);


        System.out.println("Job is done");
    }

    private static void printCustomer(Customer customer) {
        if (customer != null) {
            System.out.println(customer.getId() + "  |  " + customer.getCustomerName() + "  |  " + customer.getTelephone());
        }
    }

}
