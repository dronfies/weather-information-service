package com.dronfies.weatherinformationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class WeatherData {
    private WeatherStation weatherStation;
    private Date datetime;
    private Double windDirection;
    private Double windSpeed;
    private Unit.Speed windSpeedUnit;
    private Double airTemperature;
    private Unit.Temperature airTemperatureUnit;
    private Double dewPointTemperature;
    private Unit.Temperature dewPointTemperatureUnit;
    private Double relativeHumidity;
    private Double accumulatedPrecipitation;
    private Unit.Length accumulatedPrecipitationUnit;
    private Double atmosphericPressure;
    private Unit.Pressure atmosphericPressureUnit;
    private Double radiation;
    private Unit.Radiation radiationUnit;
}
