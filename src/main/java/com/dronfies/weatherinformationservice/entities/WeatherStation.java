package com.dronfies.weatherinformationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherStation {
    private long id;
    private String name;
    private WeatherStationNetwork network;
    private LatLngAlt latLngAlt;
    private State state;
}
