package org.openweathermap.data.report.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregatedData {
    private Long cityId;
    private String cityName;
    private Integer cloudiness;
    private String dataCalculationTime;
    private Integer timezone;
    private Float lon;
    private Float lat;
    private Float grndLevel;
    private Integer humidity;
    private Float pressure;
    private Float seaLevel;
    private Float temperature;
    private Float temperatureMin;
    private Float temperatureMax;
    private Float rainOneHour;
    private Float rainThreeHours;
    private Float snowOneHour;
    private Float snowThreeHours;
    private String country;
    private String sunrise;
    private String sunset;
    private String weatherDescription;
    private String weatherIcon;
    private Float speed;
    private Float degree;
    private Float gust;
}
