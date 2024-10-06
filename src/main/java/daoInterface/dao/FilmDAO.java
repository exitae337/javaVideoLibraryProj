package daoInterface.dao;

import Entities.Film;
import exceptions.FilmDAOException;

import java.util.List;

public interface FilmDAO {

    void addFilm(Film film) throws FilmDAOException;

    Film getFilmByID(int id) throws FilmDAOException;

    List<Film> getAllFilms() throws FilmDAOException;

    void updateFilm(Film film) throws FilmDAOException;

    void deleteFilmByID(int id) throws FilmDAOException;

}
