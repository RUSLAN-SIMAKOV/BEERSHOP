package beershop.web.filter;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.User;
import beershop.service.UserService;
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

import org.apache.log4j.Logger;

public class AuthenticationFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) {
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
                Optional<User> user = Optional.empty();
                try {
                    user = userService.getByToken(cookie.getValue());
                } catch (DataProcessingException e) {
                    LOGGER.error("Can’t get user by token", e);
                }
                if (user.isPresent()) {
                    LOGGER.info("User " + user.get().getLogin() + "authenticated");
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                }
            }
        }
        LOGGER.info("User not authenticated");
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
