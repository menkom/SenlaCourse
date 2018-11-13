package info.mastera;

import info.mastera.config.HibernateConfig;
import info.mastera.controller.IAppController;
import info.mastera.controller.impl.AppController;
import info.mastera.model.Customer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class TestExecuter {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        IAppController appController = context.getBean(AppController.class);

        Customer customer1 = new Customer();
        customer1.setCustomerName("custName");
        customer1.setTelephone("phoneNum");

        appController.createCustomer(customer1);

        List<Customer> customers = appController.getAllCustomers();

        System.out.println(customers.size());
        System.out.println(customers);
        System.out.println("something");
    }

}
