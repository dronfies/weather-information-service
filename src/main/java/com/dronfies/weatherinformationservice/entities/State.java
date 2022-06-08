package com.dronfies.weatherinformationservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class State {
    private long id;
    private Country country;
    private String name;
}
