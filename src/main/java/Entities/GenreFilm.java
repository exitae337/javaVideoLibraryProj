package Entities;

public class GenreFilm {

    private int id;
    private String genreName;

    public GenreFilm() {}

    // Getters

    public int getId() {
        return id;
    }

    public String getGenreName() {
        return genreName;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
