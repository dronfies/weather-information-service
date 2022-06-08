package com.dronfies.weatherinformationservice.controllers;

import com.dronfies.weatherinformationservice.entities.WeatherStation;
import com.dronfies.weatherinformationservice.services.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherStationController {

    @Autowired
    private WeatherStationService weatherStationService;

    @GetMapping("/weatherStations")
    public List<WeatherStation> getWeatherStations(){
        return weatherStationService.getWeatherStations();
    }

    @GetMapping("/weatherStationNetwork/{id}/weatherStations")
    public List<WeatherStation> getWeatherStationsByNetwork(long id){
        return weatherStationService.getWeatherStationsByNetwork(id);
    }

    @GetMapping("/weatherStation/{id}")
    public WeatherStation getWeatherStation(long id){
        return weatherStationService.getWeatherStation(id);
    }

    @GetMapping("/weatherStation/byLocation")
    public List<WeatherStation> getWeatherStationsByLocation(@RequestParam double lat, @RequestParam double lng, @RequestParam double distance){
        return weatherStationService.getWeatherStationsByLocation(lat, lng, distance);
    }
}
