package com.dronfies.weatherinformationservice.services;

import com.dronfies.weatherinformationservice.daos.WeatherStationNetworkDAO;
import com.dronfies.weatherinformationservice.entities.WeatherStationNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStationNetworkService {

    @Autowired
    private WeatherStationNetworkDAO weatherStationNetworkDAO;

    public List<WeatherStationNetwork> getWeatherStationNetworks(){
        return weatherStationNetworkDAO.getWeatherStationNetworks();
    }

    public WeatherStationNetwork getWeatherStationNetwork(long id){
        return weatherStationNetworkDAO.getWeatherStationNetwork(id);
    }

}
