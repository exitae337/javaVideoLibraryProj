package Entities;

public class Film {

    private int id;
    private String filmName;
    private int filmDirectorId;
    private String filmStartDate;
    private int filmCountryId;
    private int filmGenreId;

    public Film() {}

    // Getters

    public int getId() {
        return id;
    }

    public String getFilmName() {
        return filmName;
    }

    public int getFilmDirectorId() {
        return filmDirectorId;
    }

    public String getFilmStartDate() {
        return filmStartDate;
    }

    public int getFilmCountryId() {
        return filmCountryId;
    }

    public int getFilmGenreId() {
        return filmGenreId;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmDirectorId(int filmDirectorId) {
        this.filmDirectorId = filmDirectorId;
    }

    public void setFilmStartDate(String filmStartDate) {
        this.filmStartDate = filmStartDate;
    }

    public void setFilmCountryId(int filmCountryId) {
        this.filmCountryId = filmCountryId;
    }

    public void setFilmGenreId(int filmGenreId) {
        this.filmGenreId = filmGenreId;
    }
}
