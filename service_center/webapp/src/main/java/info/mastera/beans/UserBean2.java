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
public class UserBean2 extends GenericBean<User> {

    @Inject
    private IUserService<User> userService;

    private static final Logger logger = Logger.getLogger(UserBean2.class);

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
//        User user = new User();
        getService().create(user);
        clearForm();
        logger.info("User created");
    }

    public String login() {
        if (userService.isCorrectLogin(user.getUsername(), user.getPassword())) {
            User tempUser = userService.getByUsername(user.getUsername());
            logger.info("/views/loginSuccess?faces-redirect=true");

            jwtOperator.setAuthentication(FacesContext.getCurrentInstance(), tempUser);
            clearForm();
            //TODO Clear form after logging
            //TODO Create logout
            return "/views/mainPage?faces-redirect=true";
        } else {
            //TODO Correct redirect or correct user informing
            logger.info("!!/login/loginFailed?faces-redirect=true!!");
            return "/login/loginFailed?faces-redirect=true";
        }
    }

    public String logout() {
        if (isAuthorized()) {
            jwtOperator.logout(FacesContext.getCurrentInstance());
            return "/index?faces-redirect=true";
        } else {
            return "";
        }
    }

}
