package beershop.web.filter;

import beershop.exception.DataProcessingException;
import beershop.lib.Inject;
import beershop.model.Role;
import beershop.model.User;
import beershop.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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

public class AuthorizationFilter implements Filter {

    public static final String EMPTY_STRING = "";

    @Inject
    private static UserService userService;

    private static final Logger LOGGER = Logger.getLogger(AuthorizationFilter.class);

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) {
        protectedUrls.put("/servlet/getAllUsers", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/deleteUser", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/itemAdding", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/getAllItems", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/deleteItem", Role.RoleName.ADMIN);
        protectedUrls.put("/servlet/purchase", Role.RoleName.USER);
        protectedUrls.put("/servlet/bucket", Role.RoleName.USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", Role.RoleName.USER);
        protectedUrls.put("/servlet/showBucket", Role.RoleName.USER);
        protectedUrls.put("/servlet/showOrder", Role.RoleName.USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            processUnauthenticated(req, resp);
            return;
        }

        String requstedUrl = req.getRequestURI().replace(req.getContextPath(), EMPTY_STRING);
        Role.RoleName roleName = protectedUrls.get(requstedUrl);
        if (roleName == null) {
            processAuthenticated(chain, req, resp);
            return;
        }

        String token = null;
        for (Cookie cookie : cookies) {
            if ("BEERSHOP".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }

        if (token == null) {
            processUnauthenticated(req, resp);
        }

        Optional<User> user = Optional.empty();
        try {
            user = userService.getByToken(token);
        } catch (DataProcessingException e) {
            LOGGER.error("Can’t get user by token", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        if (user.isPresent()) {
            if (verifyRole(user.get(), roleName)) {
                processAuthenticated(chain, req, resp);
                return;
            }
            processDenied(req, resp);
        }
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/denied.jsp").forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        Set<Role> role = user.getRole();
        return role.stream().anyMatch(r -> r.getRoleName().equals(roleName));
    }

    private void processAuthenticated(FilterChain chain,
                                      HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        chain.doFilter(req, resp);
    }

    private void processUnauthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {
    }
}
