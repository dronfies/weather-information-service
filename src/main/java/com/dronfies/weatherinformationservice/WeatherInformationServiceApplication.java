package com.dronfies.weatherinformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherInformationServiceApplication.class, args);
	}

}
