package com.dronfies.weatherinformationservice.daos.entities;

import javax.persistence.*;

@Entity(name = "WeatherStationNetwork")
@Table(name = "weather_station_network")
public class WeatherStationNetworkEntity {

    @Id
    @SequenceGenerator(
        name = "wsn_sequence",
        sequenceName = "wsn_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "wsn_sequence"
    )
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    public WeatherStationNetworkEntity(){}

    public WeatherStationNetworkEntity(long id, String name) {
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
