package com.firstLearning.journalPrototype.controller;

import com.firstLearning.journalPrototype.api.response.WeatherResponse;
import com.firstLearning.journalPrototype.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherCheck {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<?> weatherInfo(@RequestParam(value = "city", defaultValue = "Jharkhand") String city) {
        WeatherResponse response = weatherService.getWeather(city);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
