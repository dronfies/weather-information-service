package com.dronfies.weatherinformationservice.services;

import com.dronfies.weatherinformationservice.daos.WeatherStationDAO;
import com.dronfies.weatherinformationservice.entities.WeatherStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStationService {

    @Autowired
    private WeatherStationDAO weatherStationDAO;

    public List<WeatherStation> getWeatherStations(){
        return weatherStationDAO.getWeatherStations();
    }

    public List<WeatherStation> getWeatherStationsByNetwork(long networkId){
        return weatherStationDAO.getWeatherStationsByNetworkId(networkId);
    }

    public WeatherStation getWeatherStation(long id){
        return weatherStationDAO.getWeatherStation(id);
    }

    public List<WeatherStation> getWeatherStationsByLocation(double lat, double lng, double distance) {
        return weatherStationDAO.getWeatherStations().stream().filter(station -> getDistance(lat, lng, station.getLatLngAlt().getLatitude(), station.getLatLngAlt().getLongitude()) <= distance).toList();
    }

    private double getDistance(double lat1, double lng1, double lat2, double lng2){
        double piDiv180 = Math.PI/180d;
        // Math.PI / 180
        double a = 0.5 - Math.cos((lat2 - lat1) * piDiv180)/2 + Math.cos(lat1 * piDiv180) * Math.cos(lat2 * piDiv180) * (1 - Math.cos((lng2 - lng1) * piDiv180))/2;
        return 12742 * Math.asin(Math.sqrt(a));
        // 2 * R; R = 6371 km
    }
}
