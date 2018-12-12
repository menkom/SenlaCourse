package info.mastera.utils;

import info.mastera.model.User;
import info.mastera.model.enums.UserType;
import info.mastera.util.IJwtService;
import info.mastera.util.exceptions.AuthenticationException;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
public class JwtOperator {

    private static final Logger logger = Logger.getLogger(JwtOperator.class);

    private static final String JWT_TOKEN_ATTRIBUTE = "auth";


    @Inject
    IJwtService jwtService;

    private UserType getAuthentication(HttpSession session) {
        String token = (String) session.getAttribute(JWT_TOKEN_ATTRIBUTE);
        logger.info("Token from session:" + token);
        UserType result = UserType.CUSTOMER;
        if (!StringUtils.isEmpty(token)) {
            try {
                result = jwtService.getUserGrant(token);
            } catch (AuthenticationException e) {
                logger.error(e);
//      TODO do we need to throw this exception further (higher)
            }
        }
        return result;
    }

    /**
     * This method can be used in Filter class to get user authentication type
     *
     * @param request HttpServletRequest
     * @return UserType authentication
     */
    public UserType getAuthentication(HttpServletRequest request) {
        return getAuthentication(request.getSession());
    }

    /**
     * This method can be used in Beans to get user authentication type
     *
     * @param context FacesContext
     * @return UserType authentication
     */
    public UserType getAuthentication(FacesContext context) {
        return getAuthentication((HttpSession) context.getExternalContext().getSession(false));
    }

    public void setAuthentication(FacesContext context, User user) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);

        String token = jwtService.createToken(user.getId(), user.getUserType());

        session.setAttribute(JWT_TOKEN_ATTRIBUTE, token);
        logger.info("Token saved to session:" + token);
    }

    public void logout(FacesContext context) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.removeAttribute(JWT_TOKEN_ATTRIBUTE);
        logger.info("Token removed from session.");
    }

    public boolean isAutorized(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        String token = (String) session.getAttribute(JWT_TOKEN_ATTRIBUTE);

        boolean result = false;
        if (!StringUtils.isEmpty(token)) {
            try {
                jwtService.getUserGrant(token);
                result = true;
            } catch (AuthenticationException e) {
                logger.error(e);
            }
        }
        return result;
    }

}
