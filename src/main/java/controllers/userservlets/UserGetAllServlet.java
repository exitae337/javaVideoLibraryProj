package controllers.userservlets;

import Entities.User;
import exceptions.UserDAOException;
import services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserGetAllServlet extends HttpServlet {
    private static final UsersService userService = UsersService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        String errorMessage = null;
        try {
            users = userService.getAllUsers();
        } catch (UserDAOException e) {
            errorMessage = "Не удалось получить пользователей";
        }

        req.setCharacterEncoding("UTF-8");
        req.setAttribute("users", users);
        req.setAttribute("errorMessage", errorMessage);

        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
