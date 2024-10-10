package controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        boolean isLogged = (session != null && session.getAttribute("email") != null);

        if(isLogged) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String loginURI = req.getContextPath() + "/login";
            resp.sendRedirect(loginURI);
        }
    }
}
