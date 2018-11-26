package info.mastera;

import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class UserBean extends GenericBean<User> {

    @Inject
    private IUserService<User> userService;
    private static final Logger logger = Logger.getLogger(UserBean.class);

    private Integer userId;
    private String username;
    private String password;
    private Customer customer;
    private UserType userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    @Override
    public void create() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCustomer(customer);
        user.setUserType(userType);
        getService().create(user);
        clearForm();
    }

    @Override
    protected void clearForm() {
        username = "";
        password = "";
        customer = null;
        userType = UserType.CUSTOMER;
    }

    @Override
    protected IGenericService<User> getService() {
        return userService;
    }
}
