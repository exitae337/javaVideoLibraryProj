package controllers.userservlets;

import Entities.User;
import exceptions.UserDAOException;
import services.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserRegistration extends HttpServlet {

    private static final UsersService userService = UsersService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        ServletContext servletContext = getServletContext();
        String email = req.getParameter("email");
        try {
            if(!userService.isRegistered(email)) {
                String fullName = req.getParameter("fullName");
                String password = req.getParameter("password");
                User user = new User();
                user.setEmail(email);
                user.setFullName(fullName);
                user.setPassword(password);
                user.setUserRole(3);
                userService.registerUser(user);
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(req, resp);
            } else {
                resp.getWriter().println("Пользователь с таким email уже существует в системе");
            }
        } catch (UserDAOException e) {
            resp.getWriter().println("Не удалось получить ответ от базы данных!");
        }
    }
}
