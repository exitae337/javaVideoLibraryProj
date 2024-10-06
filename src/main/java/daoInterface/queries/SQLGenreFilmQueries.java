package daoInterface.queries;

import daoInterface.columnnames.GenreFilmColumnNames;

public class SQLGenreFilmQueries {

    public static final String ADD_GENRE = "INSERT INTO genre_film ("
            + GenreFilmColumnNames.COLUMN_GENRE_NAME + ") VALUES (?)";
    public static final String GET_GENRE = "SELECT * FROM genre_film WHERE "
            + GenreFilmColumnNames.COLUMN_GENRE_ID + " = ?";
    public static final String GET_ALL_GENRES = "SELECT * FROM genre_film";
    public static final String UPDATE_GENRE = "UPDATE genre_film SET "
            + GenreFilmColumnNames.COLUMN_GENRE_NAME + " = ? WHERE "
            + GenreFilmColumnNames.COLUMN_GENRE_ID + " = ?";
    public static final String DELETE_GENRE = "DELETE FROM genre_film WHERE "
            + GenreFilmColumnNames.COLUMN_GENRE_ID + " = ?";

}
