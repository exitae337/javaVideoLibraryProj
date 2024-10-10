package controllers.userservlets;

import database.ConnectorToDatabase;
import exceptions.UserDAOException;
import services.UsersService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLogin extends HttpServlet {

    private static final UsersService usersService = UsersService.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();
        ServletContext servletContext = getServletContext();
        if(connectorToDatabase.connectionPoolInitialized()) {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(req, resp);
            throw new RuntimeException("Connection pool не инициализирован! База данных не отвечает.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            if (usersService.isLogin(email, password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("email", email);
                resp.sendRedirect("home");
            } else {
                req.setAttribute("errorMessage", "Неправильный email или пароль.");
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(req, resp);
            }
        } catch (UserDAOException e) {
            resp.getWriter().println("Не удалось получить ответ от базы данных!");
        }
    }

}
