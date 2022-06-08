package com.dronfies.weatherinformationservice.services.weatherdatasources;

import com.dronfies.weatherinformationservice.daos.WeatherDataDAO;
import com.dronfies.weatherinformationservice.entities.WeatherData;
import com.dronfies.weatherinformationservice.services.IWeatherDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseWeatherDataSource implements IWeatherDataSource {

    @Autowired
    private WeatherDataDAO weatherDataDAO;

    @Override
    public WeatherData getWeatherData(long weatherStationId) {
        return weatherDataDAO.getWeatherDataByWeatherStation(weatherStationId);
    }
}
