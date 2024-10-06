package daoInterface.queries;

import daoInterface.columnnames.FilmColumnNames;

public class SQLFilmsQueries {

    public static final String ADD_FILM = "INSERT INTO films ("
            + FilmColumnNames.COLUMN_FILM_NAME + ","
            + FilmColumnNames.COLUMN_FILM_DIRECTOR + ","
            + FilmColumnNames.COLUMN_FILM_START_DATE + ","
            + FilmColumnNames.COLUMN_FILM_COUNTRY + ","
            + FilmColumnNames.COLUMN_FILM_GENRE + ") VALUES (?, ?, ?, ?)";
    public static final String GET_FILM = "SELECT * FROM films WHERE "
            + FilmColumnNames.COLUMN_FILM_ID + " = ?";
    public static final String GET_ALL_FILMS = "SELECT * FROM films";
    public static final String UPDATE_FILM = "UPDATE films SET "
            + FilmColumnNames.COLUMN_FILM_NAME + " = ?, "
            + FilmColumnNames.COLUMN_FILM_DIRECTOR + " = ?, "
            + FilmColumnNames.COLUMN_FILM_START_DATE + " = ?, "
            + FilmColumnNames.COLUMN_FILM_COUNTRY + " = ?, "
            + FilmColumnNames.COLUMN_FILM_GENRE + " = ? WHERE "
            + FilmColumnNames.COLUMN_FILM_ID + " = ?";
    public static final String DELETE_FILM = "DELETE FROM films WHERE "
            + FilmColumnNames.COLUMN_FILM_ID + " = ?";

}
