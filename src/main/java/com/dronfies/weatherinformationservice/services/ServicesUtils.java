package com.dronfies.weatherinformationservice.services;

import com.dronfies.weatherinformationservice.entities.WeatherStation;
import com.dronfies.weatherinformationservice.services.weatherdatasources.DatabaseWeatherDataSource;
import com.dronfies.weatherinformationservice.services.weatherdatasources.NimbusWeatherDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServicesUtils {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private WeatherStationService weatherStationService;

    public IWeatherDataSource getWeatherDataSource(long weatherStationId){
        WeatherStation weatherStation = weatherStationService.getWeatherStation(weatherStationId);
        String networkName = weatherStation.getNetwork().getName();
        if(networkName.equals("INUMET") || networkName.equals("INIA") || networkName.equals("EEMAC")){
            return context.getBean(DatabaseWeatherDataSource.class);
        }else if(networkName.equals("INUMET")){
            return context.getBean(NimbusWeatherDataSource.class);
        }else{
            throw new UnsupportedOperationException(String.format("There is no IWeatherDataSource implemented for the network received [networkName=%s]", networkName));
        }
    }
}
