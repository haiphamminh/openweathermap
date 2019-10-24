package org.openweathermap.data.report.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.model.AggregatedData;

//@Mapper
public interface WeatherDataMapper {

    @Mappings({@Mapping(target = "lon", source = "coord.lon"), @Mapping(target = "lat", source = "coord.lat"), @Mapping(
            target = "grndLevel",
            source = "main.grndLevel"), @Mapping(target = "humidity",
                                                 source = "main.humidity"), @Mapping(target = "humidity",
                                                                                     source = "main.humidity")})
    AggregatedData toResponse(WeatherDataEntity entity);

    /*private Integer humidity;
    private Float pressure;
    private Float seaLevel;l
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
    private Float gust;*/
}
