package mate.academy.internetshop.web.filter;

import static mate.academy.internetshop.model.Role.RoleName.ADMIN;
import static mate.academy.internetshop.model.Role.RoleName.USER;

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
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class AuthorizationFilter implements Filter {

    public static final String EMPTY_STRING = "";

    @Inject
    private static UserService userService;

    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/deleteUser", ADMIN);
        protectedUrls.put("/servlet/itemAdding", ADMIN);
        protectedUrls.put("/servlet/getAllItems", ADMIN);
        protectedUrls.put("/servlet/deleteItem", ADMIN);
        protectedUrls.put("/servlet/purchase", USER);
        protectedUrls.put("/servlet/bucket", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
        protectedUrls.put("/servlet/showBucket", USER);
        protectedUrls.put("/servlet/showOrder", USER);
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

        Optional<User> user = userService.getByToken(token);
        if (user.isPresent()) {
            if (verifyRole(user.get(), roleName)) {
                processAuthenticated(chain, req, resp);
                return;
            }
            processDenied(req, resp);
            return;
        }
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/denied.jsp").forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        Set<Role> role = user.getRole();
        return user.getRole().stream().anyMatch(r -> r.getRoleName().equals(roleName));
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
