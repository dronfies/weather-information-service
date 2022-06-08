package com.dronfies.weatherinformationservice.controllers;

import com.dronfies.weatherinformationservice.entities.WeatherData;
import com.dronfies.weatherinformationservice.services.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherDataController {

    @Autowired
    private WeatherDataService weatherDataService;

    @GetMapping("/weatherStation/{id}/weatherData")
    public WeatherData getWeatherData(long id){
        return weatherDataService.getWeatherDataByWeatherStation(id);
    }
}
