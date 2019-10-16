package org.openweathermap.data.collector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private Long id;
    private String name;
    private Integer cod;
    private Coord coord;
    private List<Weather> weather;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Integer timezone;

    @Data
    private static class Coord {
        private Float lon;
        private Float lat;
    }

    @Data
    private static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    private static class Main {
        private Integer temp;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("temp_min")
        private Integer tempMin;
        @JsonProperty("temp_max")
        private Integer tempMax;
    }

    @Data
    private static class Wind {
        private Integer speed;
        private Integer deg;
    }

    @Data
    private static class Clouds {
        private Integer all;
    }

    @Data
    private static class Sys {
        private Integer type;
        private Long id;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
}
