package info.mastera.beans.customer;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;
import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CustomerBean extends BaseBean {

    @Inject
    private ICustomerService customerService;

    private Integer customerId;
    private String customerName;
    private String telephone;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//TODO 1. after that go to previous page. 2.If it is RequestScope then clearForm is not necessary

    public void cancel() {
        clearForm();
    }

    private void setFields(Customer customer) {
        if (customer != null) {
            clearForm();
            customerId = customer.getId();
            customerName = customer.getCustomerName();
            telephone = customer.getTelephone();
        }
    }

    public void clearForm() {
        customerId = null;
        customerName = null;
        telephone = null;
    }

    public void load() {
        if (customerId != null) {
            Customer customer = customerService.getById(customerId);
            setFields(customer);
        }
    }
    public void create() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTelephone(telephone);
        customerService.create(customer);
        clearForm();
    }

    public void update() {
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setCustomerName(customerName);
        customer.setTelephone(telephone);
        customerService.update(customer);
    }

}
