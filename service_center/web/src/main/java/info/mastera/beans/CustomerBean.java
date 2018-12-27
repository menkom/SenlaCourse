package info.mastera.beans.customer;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Customer;
import info.mastera.service.ICustomerService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CustomerBean extends BaseListBean<Customer> {

    @Inject
    private ICustomerService customerService;

    private String customerName;
    private String telephone;

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

    @Override
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @Override
    public void delete() {
        customerService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }

    private void setFields(Customer customer) {
        if (customer != null) {
            clearForm();
            setId(customer.getId());
            customerName = customer.getCustomerName();
            telephone = customer.getTelephone();
        }
    }

    public void clearForm() {
        setId(null);
        customerName = null;
        telephone = null;
    }

    public void load() {
        if (getId() != null) {
            Customer customer = customerService.getById(getId());
            setFields(customer);
        }
    }

    public void create() {
        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setTelephone(telephone);
        customerService.create(customer);
    }

    public void update() {
        Customer customer = new Customer();
        customer.setId(getId());
        customer.setCustomerName(customerName);
        customer.setTelephone(telephone);
        customerService.update(customer);
    }

}
