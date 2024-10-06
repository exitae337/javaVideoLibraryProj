package daoInterface.dao;

import Entities.CountryFilm;
import exceptions.CountryFilmDAOException;

import java.util.List;

public interface CountryFilmDAO {

    void addCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException;

    CountryFilm getCountryFilmByID(int id) throws CountryFilmDAOException;

    List<CountryFilm> getAllCountries() throws CountryFilmDAOException;

    void updateCountryFilm(CountryFilm countryFilm) throws CountryFilmDAOException;

    void deleteCountryFilmByID(int id) throws CountryFilmDAOException;

}
