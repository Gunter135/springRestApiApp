package com.example.springrestapiapp.services;

import com.example.springrestapiapp.dto.WeatherReportDTO;
import com.example.springrestapiapp.models.WeatherReport;
import com.example.springrestapiapp.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.time.LocalDateTime;
import java.util.Date;


@Service
@Transactional(readOnly = true)
public class WeatherReportService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherReportService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }


    @Transactional
    public void save(WeatherReport weatherReport){
        weatherReport.setReceivedAt(LocalDateTime.now());
        estimateWeather(weatherReport);
        weatherRepository.save(weatherReport);
    }

    public void estimateWeather(WeatherReport weatherReport){
        // counting comfort level, 10 to 8 - Great, 0 to 4 - Bad, 4 to 8 - Fine


        double temp = weatherReport.getTemperature();
        double wind = weatherReport.getWind();
        int pressure = weatherReport.getAtmosphere_pressure();
        double humidity = weatherReport.getHumidity();
        double precipitation = weatherReport.getPrecipitation();
        StringBuilder result = new StringBuilder();
        double comfort = 0;

        //Эквивалентно-эффективная температура. Показатель ЕТ (по А. Миссенарду), учитывающий влияние температуры,
        //влажности воздуха и скорости ветра при оценке тепловой чувствительности человека:
        //Т = 37 °C - (37 °C - T) / (0,68 - 0,0014F + (1 / 1,76 + 1,4V0,75)) - 0,29T(1 - F/100)
        //где T - температура воздуха, °С, V - скорость ветра, м/с , F - относительная влажность, %.
        double effective_temp = 37  - (37  - temp) / (0.68 - 0.0014*humidity +
                (1 / 1.76 + 1.4*0.75* wind)) - 0.29*temp*(1 - humidity/100.0);
        if (effective_temp > 30) {
            comfort += 0;
        } else if (effective_temp > 24) {
            comfort += 4;
        } else if (effective_temp > 18) {
            comfort += 6;
        } else if (effective_temp > 12) {
            comfort += 5;
        } else if (effective_temp > 6) {
            comfort += 4;
        } else if (effective_temp > 0) {
            comfort += 3;
        } else if (effective_temp > -6) {
            comfort += 2;
        } else if (effective_temp <= -6) {
            comfort += 0;
        }
        //среднее давление беру за 750, отклонение более чем на 10 ед считается очень неблагоприятным
        //можно реализовать сбор данных по городам но это уже другая задача
        // среднее количество осадков беру за 720 в год => 2mm per day
        comfort += 2 - (Math.abs(1 - (double)pressure/750)*100);
        if (precipitation > 20)
            comfort += 0;
        else if (precipitation > 10)
            comfort += 0.5;
        else if (precipitation > 4)
            comfort += 1;
        else if (precipitation >= 2)
            comfort += 1.5;
        else
            comfort += 2;
        if (comfort > 8)
            result.append("Great");
        else if (comfort > 4)
            result.append("Fine");
        else
            result.append("Bad");
        weatherReport.setComfort_level(String.valueOf(result));

    }

}
