package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorToDatabase {

    private static ConnectorToDatabase dbConnector = null;
    private Connection connection = null;

    private ConnectorToDatabase() {}

    private void initDatabase() {
        final String USERNAME = "root";
        final String PASSWORD = "root";
        final String URL = "jdbc:mysql://localhost:3333/DBDatabaseJava";

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to database is successful!");
        } catch (SQLException e) {
            System.out.println("Connection went wrong!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection () throws SQLException {
        connection.close();
    }

    public static ConnectorToDatabase getInstance() throws SQLException {
        if (dbConnector == null || dbConnector.getConnection().isClosed()) {
            dbConnector = new ConnectorToDatabase();
            dbConnector.initDatabase();
        }
        return dbConnector;
    }

}
