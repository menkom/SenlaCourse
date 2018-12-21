package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer", schema = "service_center")
public class Customer extends BaseObject {

    @Basic
    @Column(name = "customer_name")
    private String customerName;

    @Basic
    @Column(name = "telephone")
    private String telephone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrder> serviceOrders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<User> users;

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

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "id=" + getId() + "; "
                + "name=" + getCustomerName() + "; "
                + "phone=" + getTelephone() + "; ";
    }

    @Override
    public boolean equals(Object other) {
        Customer customer = (Customer) other;
        return super.equals(other)
                && isEqual(customerName, customer.getCustomerName())
                && isEqual(telephone, customer.getTelephone());
    }

    @Override
    public int hashCode() {
        int result = objHash(super.hashCode(), customerName);
        return objHash(result, telephone);
    }
}
