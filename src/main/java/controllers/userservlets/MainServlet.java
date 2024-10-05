package controllers.userservlets;

import database.ConnectorToDatabase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();
        ServletContext servletContext = getServletContext();
        if(connectorToDatabase.connectionPoolInitialized()) {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/main.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(req, resp);
            throw new RuntimeException("Connection pool не инициализирован! База данных не отвечает.");
        }
    }
}
