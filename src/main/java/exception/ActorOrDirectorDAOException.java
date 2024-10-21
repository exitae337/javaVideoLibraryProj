package exception;

import java.sql.SQLException;

public class ActorOrDirectorDAOException extends SQLException {

    public ActorOrDirectorDAOException() {
        super();
    }

    public ActorOrDirectorDAOException(String message) {
        super(message);
    }

    public ActorOrDirectorDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
