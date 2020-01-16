package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.UserService;

public class InjectDataController extends HttpServlet {

    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = new User();
        Role roleUser = new Role();
        user.setLogin("red");
        user.setPassword("1");
        roleUser.setRoleName((Role.RoleName.USER));
        user.addRole(roleUser);
        userService.create(user);

        User admin = new User();
        Role roleAdmin = new Role();
        admin.setLogin("blue");
        admin.setPassword("1");
        roleAdmin.setRoleName(Role.RoleName.ADMIN);
        admin.addRole(roleAdmin);
        userService.create(admin);

        resp.sendRedirect(req.getContextPath() + "/index");

    }
}
