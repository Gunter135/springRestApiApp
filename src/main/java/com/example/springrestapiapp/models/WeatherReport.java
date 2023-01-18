package com.example.springrestapiapp.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "weather_table")
public class WeatherReport {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "temperature")
    @NotNull
    private double temperature;

    @Column(name = "wind")
    @NotNull
    private double wind;

    @Column(name = "precipitation")
    @NotNull
    private double precipitation;

    @Column(name = "atmosphere_pressure")
    @NotNull
    private int atmosphere_pressure;

    @Column(name = "humidity")
    @NotNull
    private int humidity;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat
    private Date date;

    @Column(name = "receivedat")
    @NotNull
    private LocalDateTime receivedAt;

    @Column(name = "comfort_level")
    @NotNull
    private String comfort_level;

    public WeatherReport() {
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getComfort_level() {
        return comfort_level;
    }

    public void setComfort_level(String comfort_level) {
        this.comfort_level = comfort_level;
    }
}
