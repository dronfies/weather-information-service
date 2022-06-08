package com.dronfies.weatherinformationservice.daos.entities;

import com.dronfies.weatherinformationservice.entities.Unit;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "WeatherData")
@Table(name = "weather_data")
public class WeatherDataEntity {

    @Id
    @SequenceGenerator(
        name = "ws_sequence",
        sequenceName = "ws_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "ws_sequence"
    )
    private long id;

    @OneToOne
    @JoinColumn(
        name = "weather_station_id",
        referencedColumnName = "id"
    )
    private WeatherStationEntity weatherStation;

    @Column(name = "datetime", nullable = false)
    private Date datetime;

    @Column(name = "wind_direction")
    private Double windDirection;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_speed_unit")
    private Unit.Speed windSpeedUnit;

    @Column(name = "air_temperature")
    private Double airTemperature;

    @Column(name = "air_temperature_unit")
    private Unit.Temperature airTemperatureUnit;

    @Column(name = "dew_point_temperature")
    private Double dewPointTemperature;

    @Column(name = "dew_point_temperature_unit")
    private Unit.Temperature dewPointTemperatureUnit;

    @Column(name = "relative_humidity")
    private Double relativeHumidity;

    @Column(name = "accumulated_precipitation")
    private Double accumulatedPrecipitation;

    @Column(name = "accumulated_precipitation_unit")
    private Unit.Length accumulatedPrecipitationUnit;

    @Column(name = "atmospheric_pressure")
    private Double atmosphericPressure;

    @Column(name = "atmospheric_pressure_unit")
    private Unit.Pressure atmosphericPressureUnit;

    @Column(name = "radiation")
    private Double radiation;

    @Column(name = "radiation_unit")
    private Unit.Radiation radiationUnit;

    public WeatherDataEntity() {}

    public WeatherDataEntity(long id, WeatherStationEntity weatherStation, Date datetime, Double windDirection, Double windSpeed, Unit.Speed windSpeedUnit, Double airTemperature, Unit.Temperature airTemperatureUnit, Double dewPointTemperature, Unit.Temperature dewPointTemperatureUnit, Double relativeHumidity, Double accumulatedPrecipitation, Unit.Length accumulatedPrecipitationUnit, Double atmosphericPressure, Unit.Pressure atmosphericPressureUnit, Double radiation, Unit.Radiation radiationUnit) {
        this.id = id;
        this.weatherStation = weatherStation;
        this.datetime = datetime;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.windSpeedUnit = windSpeedUnit;
        this.airTemperature = airTemperature;
        this.airTemperatureUnit = airTemperatureUnit;
        this.dewPointTemperature = dewPointTemperature;
        this.dewPointTemperatureUnit = dewPointTemperatureUnit;
        this.relativeHumidity = relativeHumidity;
        this.accumulatedPrecipitation = accumulatedPrecipitation;
        this.accumulatedPrecipitationUnit = accumulatedPrecipitationUnit;
        this.atmosphericPressure = atmosphericPressure;
        this.atmosphericPressureUnit = atmosphericPressureUnit;
        this.radiation = radiation;
        this.radiationUnit = radiationUnit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WeatherStationEntity getWeatherStation() {
        return weatherStation;
    }

    public void setWeatherStation(WeatherStationEntity weatherStation) {
        this.weatherStation = weatherStation;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Unit.Speed getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public void setWindSpeedUnit(Unit.Speed windSpeedUnit) {
        this.windSpeedUnit = windSpeedUnit;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Unit.Temperature getAirTemperatureUnit() {
        return airTemperatureUnit;
    }

    public void setAirTemperatureUnit(Unit.Temperature airTemperatureUnit) {
        this.airTemperatureUnit = airTemperatureUnit;
    }

    public Double getDewPointTemperature() {
        return dewPointTemperature;
    }

    public void setDewPointTemperature(Double dewPointTemperature) {
        this.dewPointTemperature = dewPointTemperature;
    }

    public Unit.Temperature getDewPointTemperatureUnit() {
        return dewPointTemperatureUnit;
    }

    public void setDewPointTemperatureUnit(Unit.Temperature dewPointTemperatureUnit) {
        this.dewPointTemperatureUnit = dewPointTemperatureUnit;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public Double getAccumulatedPrecipitation() {
        return accumulatedPrecipitation;
    }

    public void setAccumulatedPrecipitation(Double accumulatedPrecipitation) {
        this.accumulatedPrecipitation = accumulatedPrecipitation;
    }

    public Unit.Length getAccumulatedPrecipitationUnit() {
        return accumulatedPrecipitationUnit;
    }

    public void setAccumulatedPrecipitationUnit(Unit.Length accumulatedPrecipitationUnit) {
        this.accumulatedPrecipitationUnit = accumulatedPrecipitationUnit;
    }

    public Double getAtmosphericPressure() {
        return atmosphericPressure;
    }

    public void setAtmosphericPressure(Double atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    public Unit.Pressure getAtmosphericPressureUnit() {
        return atmosphericPressureUnit;
    }

    public void setAtmosphericPressureUnit(Unit.Pressure atmosphericPressureUnit) {
        this.atmosphericPressureUnit = atmosphericPressureUnit;
    }

    public Double getRadiation() {
        return radiation;
    }

    public void setRadiation(Double radiation) {
        this.radiation = radiation;
    }

    public Unit.Radiation getRadiationUnit() {
        return radiationUnit;
    }

    public void setRadiationUnit(Unit.Radiation radiationUnit) {
        this.radiationUnit = radiationUnit;
    }
}
