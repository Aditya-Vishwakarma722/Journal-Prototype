package com.firstLearning.journalPrototype.service;

import com.firstLearning.journalPrototype.api.response.WeatherResponse;
import com.firstLearning.journalPrototype.cache.AppCache;
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

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            String finalApiUrl = appCache.APP_CACHE.get("weatherAPI").replace("<apiKey>", API_KEY);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET,
                    null, WeatherResponse.class, city);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
