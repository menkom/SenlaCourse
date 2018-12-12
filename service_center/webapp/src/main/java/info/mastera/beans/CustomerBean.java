package info.mastera.beans;

import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import info.mastera.service.IGenericService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class CustomerBean extends GenericBean<Customer> {

    private static final Logger logger = Logger.getLogger(CustomerBean.class);

    @Inject
    private ICustomerService<Customer> customerService;

    private Customer customer;

    @PostConstruct
    private void init() {
        customer = new Customer();
    }

    @PreDestroy
    public void preDestroy() {
        customer = null;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public void create() {
        Customer customer = new Customer();
        customer.setCustomerName(this.customer.getCustomerName());
        customer.setTelephone(this.customer.getTelephone());
        getService().create(customer);
        clearForm();
    }

    protected void clearForm() {
        customer = new Customer();
    }

    public void editSelect() {
        logger.info("Selected Item:" + getSelectedItem());
        logger.info("Item:" + getCustomer());
    }

    @Override
    protected IGenericService<Customer> getService() {
        return customerService;
    }

}
