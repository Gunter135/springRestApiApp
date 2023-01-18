package com.example.springrestapiapp.util;

public class WeatherReportResponseError {
    private String message;

    public WeatherReportResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
