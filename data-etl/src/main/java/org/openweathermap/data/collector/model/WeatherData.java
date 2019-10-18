package org.openweathermap.data.collector.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    private Long id;
    private String name;
    private Integer cod;
    private String base;
    private Long dt;
    private Integer timezone;
    private Integer visibility;
    private Coord coord;
    private Set<Weather> weather;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private Rain rain;
    private Snow snow;
    private Sys sys;

    @Data
    public static class Weather {
        private Long id;
        private String main;
        private String description;
        private String icon;
    }

    @Data
    public class Coord {
        private Float lon;
        private Float lat;
    }

    @Data
    public class Main {
        @JsonProperty("temp")
        private Float temperature;
        private Float pressure;
        private Integer humidity;
        @JsonProperty("temp_min")
        private Float temperatureMin;
        @JsonProperty("temp_max")
        private Float temperatureMax;
        @JsonProperty("sea_level")
        private Float seaLevel;
        @JsonProperty("grnd_level")
        private Float grndLevel;
    }

    @Data
    public class Wind {
        private Float speed;
        @JsonProperty("deg")
        private Float degree;
        private Float gust;
    }

    @Data
    public class Clouds {
        private Integer all;
    }

    @Data
    public class Rain {
        @JsonProperty("1h")
        private Float oneHour;
        @JsonProperty("3h")
        private Float threeHours;
    }

    @Data
    public class Snow {
        @JsonProperty("1h")
        private Float oneHour;
        @JsonProperty("3h")
        private Float threeHours;
    }

    @Data
    public class Sys {
        private Long id;
        private Integer type;
        private Float message;
        private String country;
        private Long sunrise;
        private Long sunset;
    }
}
