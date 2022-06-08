package com.dronfies.weatherinformationservice.daos.repositories;

import com.dronfies.weatherinformationservice.daos.entities.WeatherStationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWeatherStationRepository extends JpaRepository<WeatherStationEntity, Long> {

    @Query("SELECT w FROM WeatherStation w WHERE w.network.id = ?1")
    List<WeatherStationEntity> findWeatherStationsByNetwork(long networkId);
}
