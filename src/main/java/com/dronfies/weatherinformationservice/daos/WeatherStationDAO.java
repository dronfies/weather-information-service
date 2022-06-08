package com.dronfies.weatherinformationservice.daos;

import com.dronfies.weatherinformationservice.daos.entities.WeatherStationEntity;
import com.dronfies.weatherinformationservice.daos.exceptions.NoDataException;
import com.dronfies.weatherinformationservice.daos.repositories.IWeatherStationRepository;
import com.dronfies.weatherinformationservice.entities.WeatherStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WeatherStationDAO {

    @Autowired
    private DAOsUtils utils;

    @Autowired
    private IWeatherStationRepository weatherStationRepository;

    public List<WeatherStation> getWeatherStations(){
        return weatherStationRepository.findAll().stream().map(entity -> utils.convertToWeatherStation(entity)).toList();
    }

    public List<WeatherStation> getWeatherStationsByNetworkId(long networkId){
        return weatherStationRepository.findWeatherStationsByNetwork(networkId).stream().map(entity -> utils.convertToWeatherStation(entity)).toList();
    }

    public WeatherStation getWeatherStation(long id){
        Optional<WeatherStationEntity> optional = weatherStationRepository.findById(id);
        if(!optional.isPresent()){
            throw new NoDataException(String.format("There is no weather station with the received id [id=%d]", id));
        }
        return utils.convertToWeatherStation(optional.get());
    }
}
