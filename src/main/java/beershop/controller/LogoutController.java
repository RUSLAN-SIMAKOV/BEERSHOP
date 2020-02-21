package beershop.controller;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.getSession().invalidate();

        for (Cookie cookie : req.getCookies()) {
            if ("BEERSHOP".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                cookie.setValue("");
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
