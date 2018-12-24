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
@ViewScoped
public class CustomerBean extends BaseBean {

    private static final String URL_PAGE = "/views/customer/customerPageList";

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

    public String cancel() {
        clearForm();
        return URL_PAGE;
    }

    private void clearForm() {
        customerId = null;
        customerName = null;
        telephone = null;
    }

    public String create() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTelephone(telephone);
        customerService.create(customer);
        clearForm();
        return URL_PAGE;
    }

}
