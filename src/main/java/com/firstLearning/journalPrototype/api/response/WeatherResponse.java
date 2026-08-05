package com.firstLearning.journalPrototype.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Request request;
    private Location location;
    private Current current;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Request {
        private String type;
        private String query;
        private String language;
        private String unit;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private String name;
        private String country;
        private String region;
        private String localtime;
        @JsonProperty("timezone_id")
        private String timezoneId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Current {
        private int temperature;
        private int feelslike;
        private int humidity;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
    }
}