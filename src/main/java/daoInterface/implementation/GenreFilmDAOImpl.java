package daoInterface.implementation;

import Entities.GenreFilm;
import daoInterface.columnnames.GenreFilmColumnNames;
import daoInterface.dao.GenreFilmDAO;
import daoInterface.queries.SQLGenreFilmQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.GenreFilmDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreFilmDAOImpl implements GenreFilmDAO {

    private static volatile GenreFilmDAOImpl instance = null;
    private static final int ERROR_CODE_CONCURRENCE = 1062;
    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private GenreFilmDAOImpl() {}

    @Override
    public void addGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLGenreFilmQueries.ADD_GENRE)
        ) {
                statement.setString(1, genreFilm.getGenreName());
                statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == ERROR_CODE_CONCURRENCE) {
                System.out.println("This genre already exists!");
            } else {
                throw new GenreFilmDAOException("Error in adding film genre", e.getCause());
            }
        }
    }

    @Override
    public GenreFilm getGenreFilmByID(int id) throws GenreFilmDAOException {
        GenreFilm genreFilm = null;
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLGenreFilmQueries.GET_GENRE)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                genreFilm = new GenreFilm();
                genreFilm.setId(rs.getInt(GenreFilmColumnNames.COLUMN_GENRE_ID));
                genreFilm.setGenreName(rs.getString(GenreFilmColumnNames.COLUMN_GENRE_NAME));
            }
        } catch (SQLException e) {
            throw new GenreFilmDAOException("Error in getting genre!", e.getCause());
        }
        return genreFilm;
    }

    @Override
    public List<GenreFilm> getAllGenres() throws GenreFilmDAOException {
        List<GenreFilm> genreFilmList = new ArrayList<>();
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLGenreFilmQueries.GET_ALL_GENRES)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                GenreFilm genreFilm = new GenreFilm();
                genreFilm.setId(rs.getInt(GenreFilmColumnNames.COLUMN_GENRE_ID));
                genreFilm.setGenreName(rs.getString(GenreFilmColumnNames.COLUMN_GENRE_NAME));
                genreFilmList.add(genreFilm);
            }
        } catch (SQLException e) {
            throw new GenreFilmDAOException("Problem in getting all genres!", e.getCause());
        }
        return genreFilmList;
    }

    @Override
    public void updateGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLGenreFilmQueries.UPDATE_GENRE)
        ) {
            statement.setString(1, genreFilm.getGenreName());
            statement.setInt(2, genreFilm.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new GenreFilmDAOException("Problems with updating role", e.getCause());
        }
    }

    @Override
    public void deleteGenreFilmByID(int id) throws GenreFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLGenreFilmQueries.DELETE_GENRE)
        ) {
           statement.setInt(1, id);
           statement.executeUpdate();
        } catch (SQLException e) {
            throw new GenreFilmDAOException("problems with deleting genre!", e.getCause());
        }
    }

    public static GenreFilmDAOImpl getInstance() {
        GenreFilmDAOImpl localGenreFilm = instance;
        if (localGenreFilm == null) {
            synchronized (GenreFilmDAOImpl.class) {
                localGenreFilm = instance;
                if (localGenreFilm == null) {
                    localGenreFilm = new GenreFilmDAOImpl();
                    instance = localGenreFilm;
                }
            }
        }
        return instance;
    }

}
