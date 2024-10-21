package exception;

import java.sql.SQLException;

public class UserReviewDAOException extends SQLException {

    public UserReviewDAOException () {
        super();
    }

    public UserReviewDAOException(String message) {
        super(message);
    }

    public UserReviewDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
