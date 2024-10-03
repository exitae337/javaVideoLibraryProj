package exceptions;

import java.sql.SQLException;

public class ConnectionToDatabaseException extends SQLException {
    public ConnectionToDatabaseException() { super(); }

    public ConnectionToDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
