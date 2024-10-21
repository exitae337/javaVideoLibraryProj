package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "genre_film")
public class GenreFilm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genre_id;
    @Column
    private String genre_name;

    public GenreFilm() {}

    public int getGenre_id() {
        return genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    // Setters

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
