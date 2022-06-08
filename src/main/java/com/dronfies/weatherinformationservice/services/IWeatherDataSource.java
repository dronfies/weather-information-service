package com.dronfies.weatherinformationservice.services;

import com.dronfies.weatherinformationservice.entities.WeatherData;

public interface IWeatherDataSource {

    WeatherData getWeatherData(long weatherStationId);
}
