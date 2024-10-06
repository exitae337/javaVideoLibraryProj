package exceptions;

import java.sql.SQLException;

public class GenreFilmDAOException extends SQLException {

    public GenreFilmDAOException() {
        super();
    }

    public GenreFilmDAOException(String message) {
        super(message);
    }

    public GenreFilmDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
