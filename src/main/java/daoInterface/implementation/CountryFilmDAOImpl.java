package daoInterface.implementation;

import Entities.CountryFilm;
import daoInterface.columnnames.CountryFilmColumnNames;
import daoInterface.dao.CountryFilmDAO;
import daoInterface.queries.SQLCountryFilmQueries;
import database.ConnectionProxy;
import database.ConnectorToDatabase;
import exceptions.CountryFilmDAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryFilmDAOImpl implements CountryFilmDAO {

    private static volatile CountryFilmDAOImpl instance = null;
    private static final int ERROR_CODE_CONCURRENCE = 1062;
    private final ConnectorToDatabase connectorToDatabase = ConnectorToDatabase.getInstance();

    private CountryFilmDAOImpl() {}

    @Override
    public void addCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLCountryFilmQueries.ADD_COUNTRY)
        ) {
            statement.setString(1, countryFilm.getCountryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == ERROR_CODE_CONCURRENCE) {
                System.out.println("Such country is already exists!");
            } else {
                throw new CountryFilmDAOException("Problems with adding Country!", e.getCause());
            }
        }
    }

    @Override
    public CountryFilm getCountryFilmByID(int id) throws CountryFilmDAOException {
        CountryFilm countryFilm = null;
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLCountryFilmQueries.GET_COUNTRY)
        ) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                countryFilm = new CountryFilm();
                countryFilm.setId(rs.getInt(CountryFilmColumnNames.COLUMN_ID));
                countryFilm.setCountryName(rs.getString(CountryFilmColumnNames.COLUMN_COUNTRY_NAME));
            }
        } catch (SQLException e) {
            throw new CountryFilmDAOException("Problems with getting Country!", e.getCause());
        }
        return countryFilm;
    }

    @Override
    public List<CountryFilm> getAllCountries() throws CountryFilmDAOException {
        List<CountryFilm> countryFilmList = new ArrayList<>();
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectorToDatabase
                        .getConnection()
                        .prepareStatement(SQLCountryFilmQueries.GET_ALL_COUNTRIES)
        ) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CountryFilm countryFilm = new CountryFilm();
                countryFilm.setId(rs.getInt(CountryFilmColumnNames.COLUMN_ID));
                countryFilm.setCountryName(rs.getString(CountryFilmColumnNames.COLUMN_COUNTRY_NAME));
                countryFilmList.add(countryFilm);
            }
        } catch (SQLException e) {
            throw new CountryFilmDAOException("Problem eih getting all users!", e.getCause());
        }
        return countryFilmList;
    }

    @Override
    public void updateCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLCountryFilmQueries.UPDATE_COUNTRY)
        ) {
            statement.setString(1, countryFilm.getCountryName());
            statement.setInt(2, countryFilm.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CountryFilmDAOException("Problem in updating Country", e.getCause());
        }
    }

    @Override
    public void deleteCountryFilmByID(int id) throws CountryFilmDAOException {
        try (
                ConnectionProxy connectionProxy = connectorToDatabase
                        .getConnection();
                PreparedStatement statement = connectionProxy
                        .getRealConnection()
                        .prepareStatement(SQLCountryFilmQueries.DELETE_COUNTRY)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new CountryFilmDAOException("Problem with deleting Country", e.getCause());
        }
    }

    public static CountryFilmDAOImpl getInstance() {
        CountryFilmDAOImpl localCountryFilmInstance = instance;
        if (localCountryFilmInstance == null) {
            synchronized (CountryFilmDAOImpl.class) {
                localCountryFilmInstance = instance;
                if (localCountryFilmInstance == null) {
                    localCountryFilmInstance = new CountryFilmDAOImpl();
                    instance = localCountryFilmInstance;
                }
            }
        }
        return instance;
    }

}
