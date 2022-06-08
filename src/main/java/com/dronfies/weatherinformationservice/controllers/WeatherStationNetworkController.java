package com.dronfies.weatherinformationservice.controllers;

import com.dronfies.weatherinformationservice.entities.WeatherStationNetwork;
import com.dronfies.weatherinformationservice.services.WeatherStationNetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherStationNetworkController {

    @Autowired
    private WeatherStationNetworkService weatherStationNetworkService;

    @GetMapping("/weatherStationNetworks")
    public List<WeatherStationNetwork> getWeatherStationNetworks(){
        return weatherStationNetworkService.getWeatherStationNetworks();
    }

    @GetMapping("/weatherStationNetworks/{id}")
    public WeatherStationNetwork getWeatherStationNetwork(long id){
        return weatherStationNetworkService.getWeatherStationNetwork(id);
    }
}
