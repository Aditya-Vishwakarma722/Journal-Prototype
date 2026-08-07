package com.firstLearning.journalPrototype.service;

import com.firstLearning.journalPrototype.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String API_KEY;
    private static final String WEATHER_API_URL = "http://api.weatherstack.com/current?access_key=API_KEY&query={city}";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            String finalApiUrl = WEATHER_API_URL.replace("API_KEY", API_KEY);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET,
                    null, WeatherResponse.class, city);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
