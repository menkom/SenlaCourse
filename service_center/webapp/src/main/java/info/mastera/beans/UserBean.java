package info.mastera.beans;

import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.service.IUserService;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class UserBean extends BaseBean {

    @Inject
    private IUserService<User> userService;

    private Integer userId;
    private String username;
    private String password;
    private UserType userType;

    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

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

    public void create() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCustomer(customer);
        userService.create(user);
        clearForm();
    }

    protected void clearForm() {
        userId = null;
        username = "";
        password = "";
        userType = null;
        customer = null;
    }

}
