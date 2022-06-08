package com.dronfies.weatherinformationservice.daos;

import com.dronfies.weatherinformationservice.daos.entities.WeatherStationNetworkEntity;
import com.dronfies.weatherinformationservice.daos.exceptions.NoDataException;
import com.dronfies.weatherinformationservice.daos.repositories.IWeatherStationNetworkRepository;
import com.dronfies.weatherinformationservice.entities.WeatherStationNetwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WeatherStationNetworkDAO {

    @Autowired
    private DAOsUtils utils;

    @Autowired
    private IWeatherStationNetworkRepository weatherStationNetworkRepository;

    public List<WeatherStationNetwork> getWeatherStationNetworks(){
        return weatherStationNetworkRepository.findAll().stream().map(entity -> utils.convertToWeatherStationNetwork(entity)).toList();
    }

    public WeatherStationNetwork getWeatherStationNetwork(long id){
        Optional<WeatherStationNetworkEntity> optional = weatherStationNetworkRepository.findById(id);
        if(optional.isPresent()){
            return utils.convertToWeatherStationNetwork(optional.get());
        }else{
            throw new NoDataException(String.format("There is no weather station network with the received id [id=%d]", id));
        }
    }
}
