package com.example.springrestapiapp.repositories;

import com.example.springrestapiapp.models.WeatherReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRepository extends JpaRepository<WeatherReport,Integer> {
}
