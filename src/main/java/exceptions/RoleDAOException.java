package exceptions;

import java.sql.SQLException;

public class RoleDAOException extends SQLException {

    public RoleDAOException() {
        super();
    }

    // With error message
    public RoleDAOException(String message) {
        super(message);
    }

    // With error message & cause of the error
    public RoleDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
