package controller.userservlets;

import entity.User;
import exception.UserDAOException;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserGetByID extends HttpServlet {
    private static final UsersService userService = UsersService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIDFromForm = req.getParameter("userId");
        User user = null;
        if(userIDFromForm != null && !userIDFromForm.isEmpty() && userIDFromForm.matches("[-+]?\\d+")) {
            int userID = Integer.parseInt(userIDFromForm);
            try {
                user = userService.getUserById(userID);
            } catch (UserDAOException e) {
                resp.getWriter().println("Не удалось получить ответ от базы данных!");
            }
            req.setCharacterEncoding("UTF-8");
            req.setAttribute("user", user);
            req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else {
            resp.getWriter().write("Неправильный формат ID пользователя. Разрешены только цифры!");
        }
    }
}
