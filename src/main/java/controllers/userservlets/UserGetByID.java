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

public class UserGetByID extends HttpServlet {
    private static final UsersService userService = UsersService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIDFromForm = req.getParameter("userId");
        resp.setContentType("text/html; charset=UTF-8");
        if(userIDFromForm != null && !userIDFromForm.isEmpty() && userIDFromForm.matches("[-+]?\\d+")) {
            int userID = Integer.parseInt(userIDFromForm);
            try {
                User user = userService.getUserById(userID);
                if(user != null) {
                    try(PrintWriter printWriter = resp.getWriter()) {
                        printWriter.println("<html>");
                        printWriter.println("<head><title> Пользователь с ID:" + user.getId() + "</title></head>");
                        printWriter.println("<body>");
                        printWriter.println("<h1> Пользователь имеет следующие параметры: </h1>");
                        printWriter.println("<p>Имя пользователя:" + user.getFullName() + "</p>");
                        printWriter.println("<p>E-mail пользователя:" + user.getEmail() + "</p>");
                        printWriter.println("<p>ID роли пользователя:" + user.getRole() + "</p>");
                        printWriter.println("<p>Пароль пользователя:" + user.getPassword() + "</p>");
                        printWriter.println("</body>");
                        printWriter.println("</html>");
                    }
                } else {
                    resp.getWriter().write("Нет пользователя с таким id");
                }
            } catch (UserDAOException e) {
                resp.getWriter().println("Не удалось получить ответ от базы данных!");
            }
        } else {
            resp.getWriter().write("Неправильный формат ID пользователя. Разрешены только цифры!");
        }
    }
}
