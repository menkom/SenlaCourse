package info.mastera.model;

import info.mastera.model.base.BaseObject;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((!(o instanceof Customer)) || (!super.equals(o))) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerName(), customer.getCustomerName()) &&
                Objects.equals(getTelephone(), customer.getTelephone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCustomerName(), getTelephone());
    }

}
