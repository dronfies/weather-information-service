package com.dronfies.weatherinformationservice.entities;

public class Unit {

    public enum Speed{
        KM_PER_H, KNOT
    }

    public enum Temperature{
        CELSIUS, KELVIN, FARENHEIT
    }

    public enum Length{
        mm
    }

    public enum Pressure{
        hPa
    }

    public enum Radiation{
        mW_PER_M2
    }
}
