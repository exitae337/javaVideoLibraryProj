package entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "films")
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int film_id;
    @Column
    private String film_name;
    @Column
    private int film_director;
    @Column
    private String film_start_date;
    @Column
    private int film_country;
    @Column
    private int film_genre;

    public Film() {}

    public int getFilm_id() {
        return film_id;
    }

    public int getFilm_country() {
        return film_country;
    }

    public int getFilm_director() {
        return film_director;
    }

    public int getFilm_genre() {
        return film_genre;
    }

    public String getFilm_name() {
        return film_name;
    }

    public String getFilm_start_date() {
        return film_start_date;
    }

    // Setters


    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public void setFilm_country(int film_country) {
        this.film_country = film_country;
    }

    public void setFilm_director(int film_director) {
        this.film_director = film_director;
    }

    public void setFilm_genre(int film_genre) {
        this.film_genre = film_genre;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public void setFilm_start_date(String film_start_date) {
        this.film_start_date = film_start_date;
    }
}
