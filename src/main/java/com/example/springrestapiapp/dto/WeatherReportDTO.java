package com.example.springrestapiapp.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WeatherReportDTO {

    @NotNull
    private double temperature;

    @NotNull
    private double wind;

    @NotNull
    private double precipitation;

    @NotNull
    private int atmosphere_pressure;

    @NotNull
    private int humidity;



    @NotNull
    @DateTimeFormat
    private Date date;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public int getAtmosphere_pressure() {
        return atmosphere_pressure;
    }

    public void setAtmosphere_pressure(int atmosphere_pressure) {
        this.atmosphere_pressure = atmosphere_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
