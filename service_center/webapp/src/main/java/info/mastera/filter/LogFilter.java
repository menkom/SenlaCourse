package info.mastera.filter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"})
public class LogFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("LogFilter init!");
    }

    @Override
    public void destroy() {
        logger.info("LogFilter destroy!");
    }

    /**
     * Perform filter check on this request - Log request.
     *
     * @param servletRequest  the received request
     * @param servletResponse the response to send
     * @param filterChain     the next filter chain
     **/
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servletPath = req.getServletPath();

        logger.info("#FilterInfo :" + new Date() + ", ServletPath :" + servletPath
                + ", URL =" + req.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
