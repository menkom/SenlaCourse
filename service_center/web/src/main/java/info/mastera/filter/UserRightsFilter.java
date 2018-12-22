package info.mastera.filter;

import info.mastera.config.HibernateConfig;
import info.mastera.utils.JwtOperator;
import info.mastera.utils.UserRightsManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "userRightsFilter", urlPatterns = {"/views/*"})
public class UserRightsFilter implements Filter {

    private static final Integer STATUS_CODE_FORBIDDEN = 403;
    private static final Logger logger = Logger.getLogger(UserRightsFilter.class);

    private JwtOperator jwtOperator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        this.jwtOperator = context.getBean(JwtOperator.class);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String path = ((HttpServletRequest) servletRequest).getServletPath();
        if (UserRightsManager.checkRights(path, jwtOperator.getAuthentication(servletRequest))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/views/mainPage.xhtml");
        }
    }

    @Override
    public void destroy() {
        this.jwtOperator = null;
    }
}
