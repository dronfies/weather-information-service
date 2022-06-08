package com.dronfies.weatherinformationservice.daos.entities;

import javax.persistence.*;

@Entity(name = "WeatherStation")
@Table(name = "weather_station")
public class WeatherStationEntity {

    @Id
    @SequenceGenerator(
        name = "ws_sequence",
        sequenceName = "ws_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ws_sequence"
    )
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
        name = "network_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
            name = "network_station_fk"
        )
    )
    private WeatherStationNetworkEntity network;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "altitude")
    private Double altitude;

    @ManyToOne
    @JoinColumn(
            name = "state_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "state_station_fk"
            )
    )
    private StateEntity state;

    public WeatherStationEntity(){}

    public WeatherStationEntity(long id, String name, WeatherStationNetworkEntity network, double latitude, double longitude, Double altitude, StateEntity state) {
        this.id = id;
        this.name = name;
        this.network = network;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.state = state;
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

    public WeatherStationNetworkEntity getNetwork() {
        return network;
    }

    public void setNetwork(WeatherStationNetworkEntity network) {
        this.network = network;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public StateEntity getState() {
        return state;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }
}
