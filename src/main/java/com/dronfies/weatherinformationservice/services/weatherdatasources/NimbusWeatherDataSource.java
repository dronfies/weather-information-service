package com.dronfies.weatherinformationservice.services.weatherdatasources;

import com.dronfies.weatherinformationservice.entities.WeatherData;
import com.dronfies.weatherinformationservice.services.IWeatherDataSource;

public class NimbusWeatherDataSource implements IWeatherDataSource {
    @Override
    public WeatherData getWeatherData(long weatherStationId) {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
