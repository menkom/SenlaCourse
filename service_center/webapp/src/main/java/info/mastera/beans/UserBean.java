package info.mastera.beans;

import info.mastera.model.User;
import info.mastera.service.IGenericService;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope("session")
public class UserBean extends GenericBean<User> {

    @Inject
    private IUserService<User> userService;

    private static final Logger logger = Logger.getLogger(UserBean.class);

    private User user;

    @PostConstruct
    private void init() {
        user = new User();
    }

    @Override
    protected void clearForm() {
        user = new User();
    }

    @Override
    protected IGenericService<User> getService() {
        return userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PreDestroy
    public void preDestroy() {
        user = null;
    }


    @Override
    public void create() {
        User user = new User();
        getService().create(user);
        clearForm();
    }

    public String login() {
        if (userService.isCorrectLogin(user.getUsername(), user.getPassword())) {
            logger.info("views/loginSuccess?faces-redirect=true");
            jwtOperator.setAuthentication(FacesContext.getCurrentInstance(), user);
            clearForm();
            //TODO Clear form after logging
            //TODO Create logout
            return "views/mainPage?faces-redirect=true";
        } else {
            //TODO Correct redirect or correct user informing
            logger.info("!!loginFailed?faces-redirect=true!!");
            return "views/loginFailed?faces-redirect=true";
        }
    }

    public String logout() {
//TODO Add check is it really log in

        jwtOperator.logout(FacesContext.getCurrentInstance());
        return "index?faces-redirect=true";
    }

}
