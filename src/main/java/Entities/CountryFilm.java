package Entities;

public class CountryFilm {

    private int id;
    private String countryName;

    public CountryFilm() {}

    // Getters

    public int getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
