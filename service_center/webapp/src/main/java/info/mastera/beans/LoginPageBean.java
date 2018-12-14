package info.mastera.beans;

import info.mastera.model.User;
import info.mastera.model.UserLoginHistory;
import info.mastera.service.IUserLoginHistoryService;
import info.mastera.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

@Named
@Scope("session")
public class LoginPageBean extends BaseBean {

    private static final String USER_AUTHORIZED = "User \"%s\" authorized";
    private static final String WRONG_USERNAME_OR_PASSWORD = "Wrong username or password.";

    @Inject
    private IUserService<User> userService;
    @Inject
    private IUserLoginHistoryService<UserLoginHistory> userLoginHistoryService;

    private static final Logger logger = Logger.getLogger(LoginPageBean.class);

    private String username;
    private String password;

    private void clearForm() {
        username = "";
        password = "";
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

    public String login() {
        if (userService.isCorrectLogin(username, password)) {
            User tempUser = userService.getByUsername(username);
            setAuthentication(tempUser);
            addMessage(String.format(USER_AUTHORIZED, username));
            saveLogin(tempUser);
            clearForm();
            logger.info("redirecting to mainPage");
            return "/views/mainPage?faces-redirect=true";
        } else {
            //TODO Correct redirect or correct user informing
            addMessage(WRONG_USERNAME_OR_PASSWORD);
            logger.info("redirecting to error login page");
            return "/login/loginFailed?faces-redirect=true";
        }
    }

    private void saveLogin(User user) {
        if (user != null) {
            UserLoginHistory userLoginHistory = new UserLoginHistory();
            userLoginHistory.setUser(user);
            userLoginHistory.setLoginTime(new Date());
            userLoginHistoryService.create(userLoginHistory);
        }
    }

}
