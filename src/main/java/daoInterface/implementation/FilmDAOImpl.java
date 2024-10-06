package daoInterface.implementation;

import Entities.Film;
import daoInterface.columnnames.FilmColumnNames;
import daoInterface.dao.FilmDAO;
import daoInterface.queries.SQLFilmsQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.FilmDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    private static volatile FilmDAOImpl instance = null;
    private static final int ERROR_CODE_CONCURRENCE = 1062;
    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private FilmDAOImpl() {}

    @Override
    public void addFilm(Film film) throws FilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLFilmsQueries.ADD_FILM)
        ) {
            statement.setString(1, film.getFilmName());
            statement.setInt(2, film.getFilmDirectorId());
            statement.setString(3, film.getFilmStartDate());
            statement.setInt(4, film.getFilmCountryId());
            statement.setInt(5, film.getFilmGenreId());
            statement.executeUpdate();
        } catch (SQLException e) {
            if(e.getErrorCode() == ERROR_CODE_CONCURRENCE) {
                System.out.println("Film is already exists");
            } else {
                throw new FilmDAOException("Problem in adding film", e.getCause());
            }
        }
    }

    @Override
    public Film getFilmByID(int id) throws FilmDAOException {
        Film film = null;
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLFilmsQueries.GET_FILM)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                film = new Film();
                film.setId(rs.getInt(FilmColumnNames.COLUMN_FILM_ID));
                film.setFilmName(rs.getString(FilmColumnNames.COLUMN_FILM_NAME));
                film.setFilmDirectorId(rs.getInt(FilmColumnNames.COLUMN_FILM_DIRECTOR));
                film.setFilmStartDate(rs.getString(FilmColumnNames.COLUMN_FILM_START_DATE));
                film.setFilmCountryId(rs.getInt(FilmColumnNames.COLUMN_FILM_COUNTRY));
                film.setFilmGenreId(rs.getInt(FilmColumnNames.COLUMN_FILM_GENRE));
            }
        } catch (SQLException e) {
            throw new FilmDAOException("Problem in getting film!", e.getCause());
        }
        return film;
    }

    @Override
    public List<Film> getAllFilms() throws FilmDAOException {
        List<Film> filmList = new ArrayList<>();
        try(
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLFilmsQueries.GET_ALL_FILMS)
        ) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Film film = new Film();
                film.setId(rs.getInt(FilmColumnNames.COLUMN_FILM_ID));
                film.setFilmName(rs.getString(FilmColumnNames.COLUMN_FILM_NAME));
                film.setFilmDirectorId(rs.getInt(FilmColumnNames.COLUMN_FILM_DIRECTOR));
                film.setFilmStartDate(rs.getString(FilmColumnNames.COLUMN_FILM_START_DATE));
                film.setFilmCountryId(rs.getInt(FilmColumnNames.COLUMN_FILM_COUNTRY));
                film.setFilmGenreId(rs.getInt(FilmColumnNames.COLUMN_FILM_GENRE));
                filmList.add(film);
            }
        } catch (SQLException e) {
            throw new FilmDAOException("Problem in getting all films!", e.getCause());
        }
        return filmList;
    }

    @Override
    public void updateFilm(Film film) throws FilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLFilmsQueries.UPDATE_FILM)
        ) {
           statement.setString(1, film.getFilmName());
           statement.setInt(2, film.getFilmDirectorId());
           statement.setString(3, film.getFilmStartDate());
           statement.setInt(4, film.getFilmCountryId());
           statement.setInt(5, film.getFilmGenreId());
           statement.setInt(6, film.getId());
           statement.executeUpdate();
        } catch (SQLException e) {
            throw new FilmDAOException("Problem in updating film", e.getCause());
        }
    }

    @Override
    public void deleteFilmByID(int id) throws FilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLFilmsQueries.DELETE_FILM)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new FilmDAOException("Problems in deleting film!", e.getCause());
        }
    }

    public static FilmDAOImpl getInstance() {
        FilmDAOImpl localFilmInstance = instance;
        if (localFilmInstance == null) {
            synchronized (FilmDAOImpl.class) {
                localFilmInstance = instance;
                if(localFilmInstance == null) {
                    localFilmInstance = new FilmDAOImpl();
                    instance = localFilmInstance;
                }
            }
        }
        return instance;
    }
}
