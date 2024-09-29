package exceptions;

import java.sql.SQLException;

public class UserDAOException extends SQLException {
    public UserDAOException() {
        super();
    }
    // With error message
    public UserDAOException(String message) {
        super(message);
    }

    // With error message & cause of the error
    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
