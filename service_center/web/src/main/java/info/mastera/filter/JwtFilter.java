package info.mastera.filter;

import info.mastera.config.HibernateConfig;
import info.mastera.model.enums.UserType;
import info.mastera.utils.JwtOperator;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/views/*"})
public class JwtFilter implements Filter {

    private static final Logger logger = Logger.getLogger(JwtFilter.class);

    private JwtOperator jwtOperator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("jwtFilter init!");
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        this.jwtOperator = context.getBean(JwtOperator.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserType userType = jwtOperator.getAuthentication((HttpServletRequest) servletRequest);
        logger.info("Filter: Usertype from session token:" + userType);

        if (userType != UserType.CUSTOMER) {
            logger.info("#JwtInfo :" + new Date() +
                    ",ServletPath :" + ((HttpServletRequest) servletRequest).getServletPath()
                    + ", URL =" + ((HttpServletRequest) servletRequest).getRequestURL());
            logger.info("Logged in user.");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Redirecting to login page
            //TODO Inform user about mistake
            logger.info("Unauthorized user.");
            ((HttpServletResponse) servletResponse).sendRedirect("/login/loginPage.xhtml?faces-redirect=true");
        }
    }

    @Override
    public void destroy() {
        this.jwtOperator = null;
        logger.info("jwtFilter destroy!");
    }
}
