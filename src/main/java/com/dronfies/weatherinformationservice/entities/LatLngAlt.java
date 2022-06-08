package com.dronfies.weatherinformationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LatLngAlt {
    private double latitude;
    private double longitude;
    private double altitude;
}
