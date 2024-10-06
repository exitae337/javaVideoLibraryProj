package daoInterface.queries;

import daoInterface.columnnames.CountryFilmColumnNames;

public class SQLCountryFilmQueries {

    public static final String ADD_COUNTRY = "INSERT INTO country_film ("
            + CountryFilmColumnNames.COLUMN_COUNTRY_NAME + ") VALUES (?)";
    public static final String GET_COUNTRY = "SELECT * FROM country_film WHERE "
            + CountryFilmColumnNames.COLUMN_ID + " = ?";
    public static final String GET_ALL_COUNTRIES = "SELECT * FROM country_film";
    public static final String UPDATE_COUNTRY = "UPDATE country_film SET "
            + CountryFilmColumnNames.COLUMN_COUNTRY_NAME + " = ? WHERE "
            + CountryFilmColumnNames.COLUMN_ID + " = ?";
    public static final String DELETE_COUNTRY = "DELETE FROM country_film WHERE "
            + CountryFilmColumnNames.COLUMN_ID + " = ?";

}
