package info.mastera.beans.user;

import info.mastera.beans.base.BaseBean;
import info.mastera.beans.base.BaseListBean;
import info.mastera.model.Customer;
import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.service.IUserService;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class UserBean extends BaseListBean<User> {

    @Inject
    private IUserService userService;

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

    @Override
    public List<User> getAll() {
        return userService.getAllAndCustomer();
    }

    @Override
    public void delete() {
        userService.delete(getSelectedItem());
        if (getSelectedItem() != null) {
            addMessage(String.format(MESSAGE_ITEM_DELETED, getSelectedItem().getId()));
        }
        clearSelected();
    }
    public void create() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCustomer(customer);
        userService.create(user);
    }

    public void clearForm() {
        setId(null);
        username = null;
        password = null;
        userType = null;
        customer = null;
    }

    public void load() {
        if (getId() != null) {
            User user = userService.getByIdWithCustomer(getId());
            setFields(user);
        }
    }

    private void setFields(User user) {
        if (user != null) {
            clearForm();
            setId(user.getId());
            username = user.getUsername();
            password = user.getPassword();
            userType = user.getUserType();
            customer = user.getCustomer();
        }
    }

    public void update() {
        User user = new User();
        user.setId(getId());
        user.setUsername(username);
        user.setPassword(password);
        user.setUserType(userType);
        user.setCustomer(customer);
        userService.update(user);
//TODO Point to updated object. Make it select
    }

}
