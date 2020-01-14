package mate.academy.internetshop.web.filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

    private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getCookies() == null) {
            processUnauthenticated(req, resp);
            return;
        }
        for (Cookie cookie : req.getCookies()) {
            if ("BEERSHOP".equals(cookie.getName())) {
                Optional<User> user = userService.getByToken(cookie.getValue());
                if (user.isPresent()) {
                    logger.info("User " + user.get().getLogin() + "authenticated");
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        logger.info("User not authenticated");
        processUnauthenticated(req, resp);
    }

    private void processUnauthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
