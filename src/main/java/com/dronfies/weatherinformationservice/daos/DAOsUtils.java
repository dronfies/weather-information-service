package com.dronfies.weatherinformationservice.daos;

import com.dronfies.weatherinformationservice.daos.entities.*;
import com.dronfies.weatherinformationservice.entities.*;
import org.springframework.stereotype.Component;

@Component
public class DAOsUtils {
    public WeatherStationNetwork convertToWeatherStationNetwork(WeatherStationNetworkEntity entity){
        return new WeatherStationNetwork(entity.getId(), entity.getName());
    }

    public WeatherStation convertToWeatherStation(WeatherStationEntity weatherStationEntity){
        return new WeatherStation(
            weatherStationEntity.getId(),
            weatherStationEntity.getName(),
            convertToWeatherStationNetwork(weatherStationEntity.getNetwork()),
            new LatLngAlt(
                weatherStationEntity.getLatitude(),
                weatherStationEntity.getLongitude(),
                weatherStationEntity.getAltitude()
            ),
            convertToState(weatherStationEntity.getState())
        );
    }

    public State convertToState(StateEntity entity){
        return new State(entity.getId(), convertToCountry(entity.getCountry()), entity.getName());
    }

    public Country convertToCountry(CountryEntity entity){
        return new Country(entity.getId(), entity.getName());
    }

    public WeatherData convertToWeatherData(WeatherDataEntity entity){
        return new WeatherData(
            convertToWeatherStation(entity.getWeatherStation()),
            entity.getDatetime(),
            entity.getWindDirection(),
            entity.getWindSpeed(),
            entity.getWindSpeedUnit(),
            entity.getAirTemperature(),
            entity.getAirTemperatureUnit(),
            entity.getDewPointTemperature(),
            entity.getDewPointTemperatureUnit(),
            entity.getRelativeHumidity(),
            entity.getAccumulatedPrecipitation(),
            entity.getAccumulatedPrecipitationUnit(),
            entity.getAtmosphericPressure(),
            entity.getAtmosphericPressureUnit(),
            entity.getRadiation(),
            entity.getRadiationUnit()
        );
    }

    public WeatherStationNetworkEntity convertToWeatherStationNetworkEntity(WeatherStationNetwork network){
        if(network == null) return null;
        return new WeatherStationNetworkEntity(
            network.getId(),
            network.getName()
        );
    }

    public StateEntity convertToStateEntity(State state){
        if(state == null) return null;
        return new StateEntity(
            state.getId(),
            state.getName(),
            new CountryEntity(
                state.getCountry().getId(),
                state.getCountry().getName()
            )
        );
    }

    public WeatherStationEntity convertToWeatherStationEntity(WeatherStation weatherStation){
        return new WeatherStationEntity(
                weatherStation.getId(),
                weatherStation.getName(),
                convertToWeatherStationNetworkEntity(weatherStation.getNetwork()),
                weatherStation.getLatLngAlt().getLatitude(),
                weatherStation.getLatLngAlt().getLongitude(),
                weatherStation.getLatLngAlt().getAltitude(),
                convertToStateEntity(weatherStation.getState())
        );
    }

    public WeatherDataEntity convertToWeatherDataEntity(WeatherData weatherData){
        return new WeatherDataEntity(
            -1,
            convertToWeatherStationEntity(weatherData.getWeatherStation()),
            weatherData.getDatetime(),
            weatherData.getWindDirection(),
            weatherData.getWindSpeed(),
            weatherData.getWindSpeedUnit(),
            weatherData.getAirTemperature(),
            weatherData.getAirTemperatureUnit(),
            weatherData.getDewPointTemperature(),
            weatherData.getDewPointTemperatureUnit(),
            weatherData.getRelativeHumidity(),
            weatherData.getAccumulatedPrecipitation(),
            weatherData.getAccumulatedPrecipitationUnit(),
            weatherData.getAtmosphericPressure(),
            weatherData.getAtmosphericPressureUnit(),
            weatherData.getRadiation(),
            weatherData.getRadiationUnit()
        );
    }
}
