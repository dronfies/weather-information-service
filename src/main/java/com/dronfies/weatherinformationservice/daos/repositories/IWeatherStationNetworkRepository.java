package com.dronfies.weatherinformationservice.daos.repositories;

import com.dronfies.weatherinformationservice.daos.entities.WeatherStationNetworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWeatherStationNetworkRepository extends JpaRepository<WeatherStationNetworkEntity, Long> {
}
