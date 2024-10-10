package daoInterface.dao;

import Entities.GenreFilm;
import exceptions.GenreFilmDAOException;

import java.util.List;

public interface GenreFilmDAO {

    void addGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException;

    GenreFilm getGenreFilmByID(int id) throws GenreFilmDAOException;

    List<GenreFilm> getAllGenres() throws GenreFilmDAOException;

    void updateGenreFilm(GenreFilm genreFilm) throws GenreFilmDAOException;

    void deleteGenreFilmByID(int id) throws GenreFilmDAOException;

}