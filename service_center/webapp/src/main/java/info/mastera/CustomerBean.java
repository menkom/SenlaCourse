package info.mastera;

import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope("session")
public class CustomerBean {

    private static final Logger logger = Logger.getLogger(CustomerBean.class);

    @Inject
    private ICustomerService customerService;

    private String customerName;
    private String customerPhone;

    private Customer selectedCustomer;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Customer getselectedCustomer() {
        return selectedCustomer;
    }

    public void setselectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }

    public void create() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTelephone(customerPhone);
        customerService.create(customer);
        clearForm();
    }

    private void clearForm() {
        setCustomerName("");
        setCustomerPhone("");
    }

    public List<Customer> getAll() {
        logger.info("Abstract.getAll; " + this.getClass());
        return customerService.getAll();
    }

    public void deleteCustomer() {
        customerService.delete(selectedCustomer);
        selectedCustomer = null;
    }

}
