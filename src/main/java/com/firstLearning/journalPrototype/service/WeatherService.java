package com.firstLearning.journalPrototype.service;

import com.firstLearning.journalPrototype.api.response.WeatherResponse;
import com.firstLearning.journalPrototype.cache.AppCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class WeatherService {

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            String weatherApiPattern = appCache.get("weatherAPI");
            if (weatherApiPattern == null || weatherApiPattern.trim().isEmpty()) {
                log.error("Weather API URL configuration missing or null in AppCache ('weatherAPI')");
                return null;
            }
            String apiKey = appCache.get("apiKey");
            if (apiKey == null || apiKey.trim().isEmpty()) {
                apiKey = appCache.get("weather_api_key");
            }
            if (apiKey == null || apiKey.trim().isEmpty()) {
                log.error("Weather API Key missing in AppCache ('apiKey')");
                return null;
            }
            String finalApiUrl = weatherApiPattern.replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApiUrl, HttpMethod.GET,
                    null, WeatherResponse.class, city);
            WeatherResponse body = response.getBody();
            if (body != null && (!body.isSuccess() || body.getError() != null)) {
                if (body.getError() != null) {
                    log.error("Weather API returned error code {}: {}", body.getError().getCode(), body.getError().getInfo());
                } else {
                    log.error("Weather API request failed with unsuccessful response");
                }
                return null;
            }
            return body;
        } catch (Exception e) {
            log.error("Exception occurred while fetching weather for city {}: ", city, e);
            return null;
        }
    }
}

