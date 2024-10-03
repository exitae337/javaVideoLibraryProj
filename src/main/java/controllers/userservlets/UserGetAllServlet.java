package controllers.userservlets;

import Entities.User;
import exceptions.ConnectionToDatabaseException;
import exceptions.UserDAOException;
import services.UsersService;

import javax.servlet.ServletConfig;
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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() { }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try {
            List<User> users = userService.getAllUsers();
            try (PrintWriter printWriter = resp.getWriter()) {
                printWriter.println("<html>");
                printWriter.println("<head><title> Все пользователи </title></head>");
                printWriter.println("<body>");
                printWriter.println("<h1> Список всех пользователей: </h1>");
                printWriter.println("<table border='1'>");
                printWriter.println("<tr><td>ID</td><td>Имя</td><td>E-mail</td><td>Роль</td></tr>");

                for(User user: users) {
                    printWriter.println("<tr>");
                    printWriter.println("<td>" + user.getId() + "</td>");
                    printWriter.println("<td>" + user.getFullName() + "</td>");
                    printWriter.println("<td>" + user.getEmail() + "</td>");
                    printWriter.println("<td>" + user.getRole() + "</td>");
                    printWriter.println("</tr>");
                }

                printWriter.println("</table>");
                printWriter.println("</body>");
                printWriter.println("</html>");
            }
        } catch (UserDAOException e) {
            resp.getWriter().println("Не удалось получить ответ от базы данных!");
        }
    }
}
