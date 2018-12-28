package info.mastera.model;

import info.mastera.model.base.BaseObject;
import info.mastera.model.enums.UserType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * User - user with login and password to use this program
 */
@Entity
@Table(name = "user", schema = "service_center")
public class User extends BaseObject {

    @Basic
    @Column(name = "username")
    private String username;

    //    Todo encode password (ShaPassword)
    @Basic
    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrderJob> jobs;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ServiceOrderPart> serviceOrderParts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<UserLoginHistory> userLoginHistories;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<ServiceOrderJob> getJobs() {
        return jobs;
    }

    public void setJobs(List<ServiceOrderJob> jobs) {
        this.jobs = jobs;
    }

    public List<ServiceOrderPart> getServiceOrderParts() {
        return serviceOrderParts;
    }

    public void setServiceOrderParts(List<ServiceOrderPart> serviceOrderParts) {
        this.serviceOrderParts = serviceOrderParts;
    }

    public List<UserLoginHistory> getUserLoginHistories() {
        return userLoginHistories;
    }

    public void setUserLoginHistories(List<UserLoginHistory> userLoginHistories) {
        this.userLoginHistories = userLoginHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((!(o instanceof User)) || (!super.equals(o))) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getUserType() == user.getUserType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername(), getPassword(), getUserType());
    }
}
