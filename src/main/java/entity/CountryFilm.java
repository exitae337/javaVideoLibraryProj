package entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "country_film")
public class CountryFilm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int country_id;
    @Column
    private String country_name;

    public CountryFilm() {}

    public int getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    //Setters

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }
}
