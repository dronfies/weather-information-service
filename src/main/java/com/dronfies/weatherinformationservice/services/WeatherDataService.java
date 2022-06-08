package com.dronfies.weatherinformationservice.services;

import com.dronfies.weatherinformationservice.entities.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherDataService {

    @Autowired
    private ServicesUtils utils;

    public WeatherData getWeatherDataByWeatherStation(long id) {
        return utils.getWeatherDataSource(id).getWeatherData(id);
    }
}
