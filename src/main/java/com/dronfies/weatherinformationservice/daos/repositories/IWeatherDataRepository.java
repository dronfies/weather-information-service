package com.dronfies.weatherinformationservice.daos.repositories;

import com.dronfies.weatherinformationservice.daos.entities.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IWeatherDataRepository extends JpaRepository<WeatherDataEntity, Long> {

    @Query("SELECT w FROM WeatherData w WHERE w.weatherStation.id = ?1")
    Optional<WeatherDataEntity> findWeatherDataByStation(long stationId);

    @Transactional
    @Modifying
    @Query("DELETE FROM WeatherData w WHERE w.weatherStation.id = ?1")
    void deleteWeatherDataByStation(long stationId);
}
