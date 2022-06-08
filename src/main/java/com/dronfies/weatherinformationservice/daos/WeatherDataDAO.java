package com.dronfies.weatherinformationservice.daos;

import com.dronfies.weatherinformationservice.daos.entities.WeatherDataEntity;
import com.dronfies.weatherinformationservice.daos.exceptions.NoDataException;
import com.dronfies.weatherinformationservice.daos.repositories.IWeatherDataRepository;
import com.dronfies.weatherinformationservice.entities.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WeatherDataDAO {

    @Autowired
    private DAOsUtils utils;

    @Autowired
    private WeatherStationDAO weatherStationDAO;

    @Autowired
    private IWeatherDataRepository weatherDataRepository;

    public WeatherData getWeatherDataByWeatherStation(long id) {
        // verify there is a weather station with the id received
        weatherStationDAO.getWeatherStation(id);

        // get weather data
        Optional<WeatherDataEntity> entity = weatherDataRepository.findWeatherDataByStation(id);
        if(!entity.isPresent()){
            throw new NoDataException(String.format("There is no weather data for the station with the id received [id=%d]", id));
        }
        return utils.convertToWeatherData(entity.get());
    }

    public void updateWeatherData(List<WeatherData> listWeatherData){
        for(WeatherData weatherData : listWeatherData){
            weatherDataRepository.deleteWeatherDataByStation(weatherData.getWeatherStation().getId());
        }
        List<WeatherDataEntity> entities = listWeatherData.stream().map(weatherData -> utils.convertToWeatherDataEntity(weatherData)).toList();
        weatherDataRepository.saveAll(entities);
    }
}
