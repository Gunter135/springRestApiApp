package com.example.springrestapiapp.controlles;


import com.example.springrestapiapp.dto.WeatherReportDTO;
import com.example.springrestapiapp.models.WeatherReport;
import com.example.springrestapiapp.services.WeatherReportService;
import com.example.springrestapiapp.util.WeatherReportIncorrectFormException;
import com.example.springrestapiapp.util.WeatherReportResponseError;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/weather_forecast")
public class WeatherController {

    private final WeatherReportService weatherReportService;
    private final ModelMapper modelMapper;

    @Autowired
    public WeatherController(WeatherReportService weatherReportService,
                             ModelMapper modelMapper) {
        this.weatherReportService = weatherReportService;
        this.modelMapper = modelMapper;
    }

    //MAPPING

    @PostMapping(value ="/report")
    public ResponseEntity<HttpStatus> appendResult(@RequestBody @Valid WeatherReportDTO weatherReportDTO,
                                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMSG = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMSG.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new WeatherReportIncorrectFormException(errorMSG.toString());
        }
        weatherReportService.save(convertToWeather(weatherReportDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    // EXCEPTION HANDLERS

    @ExceptionHandler
    private ResponseEntity<WeatherReportResponseError> handleException(WeatherReportIncorrectFormException e){
        WeatherReportResponseError response = new WeatherReportResponseError(e.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    // DTO CONVERTERS

    private WeatherReportDTO convertToWeatherDTO(WeatherReport weatherReport){
        return modelMapper.map(weatherReport,WeatherReportDTO.class);
    }
    private WeatherReport convertToWeather(WeatherReportDTO weatherReportDTO){
        return modelMapper.map(weatherReportDTO,WeatherReport.class);
    }
}
