package database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ConnectionPool {
    private final BlockingQueue<ConnectionProxy> arrayOfConnections;
    private static final String USERNAME = "database.username";
    private static final String PASSWORD = "database.password";
    private static final String URL = "database.url";
    private static final Properties properties = new Properties();
    private final int MAX_CONNECTIONS = 10;

    static {
        try {
            properties.load(Files.newInputStream(Paths
                    .get("src/main/resources/application.properties")));
        } catch (IOException e) {
            System.out.println("File 'application.properties' not found!'");
        }
    }
    public ConnectionPool() {
        arrayOfConnections = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
        initConnectionPool();
    }
    private void initConnectionPool() {
        for(int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                Connection connection = DriverManager.getConnection(
                        properties.getProperty(URL),
                        properties.getProperty(USERNAME),
                        properties.getProperty(PASSWORD)
                );
                arrayOfConnections.add(new ConnectionProxy(connection, this));
            } catch (SQLException e) {
                System.out.println("Problems with connection to database!");
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
    public void shutdownAllConnections() throws SQLException {
        for(ConnectionProxy connection: arrayOfConnections) {
            if (connection != null) {
                connection.getRealConnection().close();
            }
        }
        arrayOfConnections.clear();
    }
}
