package database.myConnectionPool;

import exception.ConnectionToDatabaseException;

public class ConnectorToDatabase {
    private static volatile ConnectorToDatabase dbConnector = null;
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private ConnectorToDatabase() {}

    public ConnectionProxy getConnection() throws ConnectionToDatabaseException {
        try {
            return connectionPool.getConnection();
        } catch (InterruptedException e) {
            throw new ConnectionToDatabaseException();
        }
    }

    public boolean connectionPoolInitialized() {
        return connectionPool != null;
    }

    public static ConnectorToDatabase getInstance() {
        ConnectorToDatabase localConnector = dbConnector;
        if (localConnector == null) {
            synchronized (ConnectorToDatabase.class) {
                localConnector = dbConnector;
                if (localConnector == null) {
                    localConnector = new ConnectorToDatabase();
                    dbConnector = localConnector;
                }
            }
        }
        return dbConnector;
    }

}
