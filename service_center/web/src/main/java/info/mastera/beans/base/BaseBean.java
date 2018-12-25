package info.mastera.beans.base;

import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.utils.JwtOperator;
import org.apache.log4j.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
public abstract class BaseBean implements Serializable {

    @Inject
    private JwtOperator jwtOperator;

    private Integer id;

    protected void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    protected boolean isAuthorized() {
        return jwtOperator.isAutorized();
    }

    protected UserType getAuthentication() {
        return jwtOperator.getAuthentication(FacesContext.getCurrentInstance());
    }

    protected void setAuthentication(User user) {
        jwtOperator.setAuthentication(FacesContext.getCurrentInstance(), user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String logout() {
        if (isAuthorized()) {
            jwtOperator.logout(FacesContext.getCurrentInstance());
            return "/login/loginPage?faces-redirect=true";
        } else {
            return "";
        }
    }

}
