package exception;

import java.sql.SQLException;

public class CountryFilmDAOException extends SQLException {

    public CountryFilmDAOException() {
        super();
    }

    public CountryFilmDAOException(String message) {
        super(message);
    }

    public CountryFilmDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
