package ru.teligent.weatherforecast.cityDB.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CityInformation {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String country;

    public CityInformation(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public CityInformation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
