package com.dronfies.weatherinformationservice.daos.entities;

import javax.persistence.*;

@Entity(name = "State")
@Table(name = "state")
public class StateEntity {

    @Id
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "country_state_fk"
            )
    )
    private CountryEntity country;

    public StateEntity(){}

    public StateEntity(long id, String name, CountryEntity country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }
}
