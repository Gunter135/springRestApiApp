package com.example.springrestapiapp.util;

public class WeatherReportIncorrectFormException extends RuntimeException {
    public WeatherReportIncorrectFormException(String message) {
        super(message);
    }
}
