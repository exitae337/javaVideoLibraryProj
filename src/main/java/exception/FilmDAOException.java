package exception;

import java.sql.SQLException;

public class FilmDAOException extends SQLException {

    public FilmDAOException() {
        super();
    }

    public FilmDAOException(String message) {
        super(message);
    }

    public FilmDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
