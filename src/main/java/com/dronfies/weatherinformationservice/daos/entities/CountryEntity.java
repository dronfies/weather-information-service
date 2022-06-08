package com.dronfies.weatherinformationservice.daos.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Country")
@Table(name = "country")
public class CountryEntity {

    @Id
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public CountryEntity(){}

    public CountryEntity(long id, String name) {
        this.id = id;
        this.name = name;
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
}
