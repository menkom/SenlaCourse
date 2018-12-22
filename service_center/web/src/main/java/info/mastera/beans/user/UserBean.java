package info.mastera.beans.user;

import info.mastera.beans.base.BaseBean;
import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.service.IUserService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
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

    public String create() {
//TODO We need to hide "create" button when we have ID
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCustomer(customer);
        userService.create(user);
        clearForm();
        return "/views/user/userPageList.xhtml";
    }

    private void clearForm() {
        userId = null;
        username = "";
        password = "";
        userType = null;
        customer = null;
    }

    public String load() {
        if (userId != null) {
            User user = userService.getById(userId);
            setFields(user);
        }
        return "/views/user/userPage.xhtml";
    }

    public String loadToCreate() {
        clearForm();
        return "/views/user/userPage.xhtml";
    }


    private void setFields(User user) {
        if (user != null) {
            clearForm();
            userId = user.getId();
            username = user.getUsername();
            password = user.getPassword();
            userType = user.getUserType();
            customer = user.getCustomer();
        }
    }

    public String update() {
        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCustomer(customer);
        userService.update(user);
        clearForm();
//TODO 1. after that go to previous page. 2.If it is RequestScope then clearForm is not necessary
        return "/views/user/userPageList.xhtml";
//TODO Point to updated object. Make it select
    }

    public String cancel() {
        clearForm();
        return "/views/user/userPageList.xhtml";
    }

}
