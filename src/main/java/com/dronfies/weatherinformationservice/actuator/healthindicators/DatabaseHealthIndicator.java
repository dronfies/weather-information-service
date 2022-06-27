package com.dronfies.weatherinformationservice.actuator.healthindicators;

import com.dronfies.weatherinformationservice.daos.WeatherStationNetworkDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

    @Autowired
    private WeatherStationNetworkDAO weatherStationNetworkDAO;

    @Override
    public Health health() {
        if(isDatabaseRunning()){
            return Health.up().build();
        }else{
            return Health.down().build();
        }
    }

    private boolean isDatabaseRunning(){
        try{
            weatherStationNetworkDAO.getWeatherStationNetworks();
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
