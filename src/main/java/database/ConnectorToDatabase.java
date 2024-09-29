package database;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectorToDatabase {
    private static volatile ConnectorToDatabase dbConnector = null;
    private static final ConnectionPool connectionPool = new ConnectionPool();

    private ConnectorToDatabase() {}

    public ConnectionProxy getConnection() throws SQLException {
        try {
            return connectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectorToDatabase getInstance() {
        ConnectorToDatabase localConnector = dbConnector;
        if (localConnector == null) {
            synchronized (ConnectorToDatabase.class) {
                localConnector = dbConnector;
                if (localConnector == null) {
                    dbConnector = localConnector = new ConnectorToDatabase();
                }
            }
        }
        return dbConnector;
    }

}
