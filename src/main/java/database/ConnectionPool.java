package database;

import exceptions.ConnectionToDatabaseException;
import exceptions.UserDAOException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionPool {

    private static volatile ConnectionPool instance = null;
    private final BlockingQueue<ConnectionProxy> arrayOfConnections;
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";
    private static final String URL = "database.url";
    private static final String JDBC_DRIVER = "database.jdbc.driver";
    private static final Properties properties = new Properties();
    private final int MAX_CONNECTIONS = 10;

    static {
        try(
                InputStream fis = ConnectionPool.class.getClassLoader().
                        getResourceAsStream("application.properties");
        ) {
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("File 'application.properties' not found!'");
        }
    }
    private ConnectionPool() throws ConnectionToDatabaseException {
        arrayOfConnections = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
        initConnectionPool();
    }

    private void initConnectionPool() throws ConnectionToDatabaseException {
        try {
            Class.forName(properties.getProperty(JDBC_DRIVER));
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found.");
            return;
        }
        for(int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                Connection connection = DriverManager.getConnection(
                        properties.getProperty(URL),
                        properties.getProperty(USERNAME),
                        properties.getProperty(PASSWORD)
                );
                arrayOfConnections.add(new ConnectionProxy(connection, this));
            } catch (SQLException e) {
                throw new ConnectionToDatabaseException("Problem with connection", e.getCause());
            }
        }
    }
    public ConnectionProxy getConnection() throws InterruptedException {
        return arrayOfConnections.take();
    }
    public void returnConnection(ConnectionProxy connection) throws InterruptedException {
        if(connection != null) {
            arrayOfConnections.put(connection);
        }
    }
    public void shutdownAllConnections() throws UserDAOException {
        for(ConnectionProxy connection: arrayOfConnections) {
            if (connection != null) {
                connection.closeRealConnection();
            }
        }
        arrayOfConnections.clear();
    }

    public static ConnectionPool getInstance() {
        ConnectionPool localInstance = instance;
        if(localInstance == null) {
            synchronized (ConnectionPool.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        localInstance = new ConnectionPool();
                        instance = localInstance;
                    } catch (ConnectionToDatabaseException e) {
                        return null;
                    }
                }
            }
        }
        return instance;
    }
}
