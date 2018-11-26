package info.mastera;

import info.mastera.model.Customer;
import info.mastera.model.base.BaseObject;
import info.mastera.service.ICustomerService;
import info.mastera.service.IGenericService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class CustomerBean extends GenericBean<Customer> {

    private static final Logger logger = Logger.getLogger(CustomerBean.class);

    @Inject
    private ICustomerService<Customer> customerService;

    private Integer customerId;
    private String customerName;
    private String customerPhone;

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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public void create() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTelephone(customerPhone);
        getService().create(customer);
        clearForm();
    }

    protected void clearForm() {
        setCustomerId(null);
        setCustomerName("");
        setCustomerPhone("");
    }

    @Override
    protected IGenericService<Customer> getService() {
        return customerService;
    }

}
