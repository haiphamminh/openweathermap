package org.openweathermap.data.etl.component;

import org.openweathermap.data.collector.model.WeatherData;
import org.openweathermap.data.collector.model.WeatherData.Coord;
import org.openweathermap.data.collector.model.WeatherData.Main;
import org.openweathermap.data.collector.model.WeatherData.Rain;
import org.openweathermap.data.collector.model.WeatherData.Snow;
import org.openweathermap.data.collector.model.WeatherData.Sys;
import org.openweathermap.data.collector.model.WeatherData.Weather;
import org.openweathermap.data.collector.model.WeatherData.Wind;
import org.openweathermap.data.etl.domain.CoordEntity;
import org.openweathermap.data.etl.domain.MainEntity;
import org.openweathermap.data.etl.domain.RainEntity;
import org.openweathermap.data.etl.domain.SnowEntity;
import org.openweathermap.data.etl.domain.SysEntity;
import org.openweathermap.data.etl.domain.WeatherDataEntity;
import org.openweathermap.data.etl.domain.WeatherEntity;
import org.openweathermap.data.etl.domain.WindEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataConverter {
    public static void main(String[] args) {
        DataConverter converter = new DataConverter();
        System.out.println(converter.convert(1571383815));
    }

    public List<WeatherDataEntity> convert(List<WeatherData> data) {
        return data.stream()
                   .map(this::convert)
                   .collect(Collectors.toList());
    }

    public WeatherDataEntity convert(WeatherData weatherData) {
        return WeatherDataEntity.builder()
                                .cityId(weatherData.getId())
                                .cityName(weatherData.getName())
                                .cloudiness(weatherData.getClouds()
                                                       .getAll())
                                .timezone(weatherData.getTimezone())
                                .dataCalculationTime(convert(weatherData.getDt() * 1000))
                                .coord(convert(weatherData.getCoord()))
                                .main(convert(weatherData.getMain()))
                                .rain(convert(weatherData.getRain()))
                                .snow(convert(weatherData.getSnow()))
                                .sys(convert(weatherData.getSys()))
                                .wind(convert(weatherData.getWind()))
                                .weathers(convert(weatherData.getWeather()))
                                .build();
    }

    public Set<WeatherEntity> convert(Set<Weather> weathers) {
        if (CollectionUtils.isEmpty(weathers)) {
            return Collections.emptySet();
        }
        return weathers.stream()
                       .map(this::convert)
                       .collect(Collectors.toSet());
    }

    public WeatherEntity convert(Weather weather) {
        if (weather == null) return null;

        return WeatherEntity.builder()
                            .description(weather.getDescription())
                            .icon(weather.getIcon())
                            .main(weather.getMain())
                            .providerId(weather.getId())
                            .build();
    }

    public WindEntity convert(Wind wind) {
        if (wind == null) return null;
        return WindEntity.builder()
                         .degree(wind.getDegree())
                         .gust(wind.getGust())
                         .speed(wind.getSpeed())
                         .build();
    }

    public SysEntity convert(Sys sys) {
        if (sys == null) return null;
        return SysEntity.builder()
                        .country(sys.getCountry())
                        .sunrise(convert(sys.getSunrise() * 1000))
                        .sunset(convert(sys.getSunset() * 1000))
                        .build();
    }

    public SnowEntity convert(Snow snow) {
        if (snow == null) {
            return null;
        }
        return SnowEntity.builder()
                         .oneHour(snow.getOneHour())
                         .threeHours(snow.getThreeHours())
                         .build();
    }

    public RainEntity convert(Rain rain) {
        if (rain == null) return null;
        return RainEntity.builder()
                         .oneHour(rain.getOneHour())
                         .threeHours(rain.getThreeHours())
                         .build();
    }

    public CoordEntity convert(Coord coord) {
        if (coord == null) return null;
        return CoordEntity.builder()
                          .lat(coord.getLat())
                          .lon(coord.getLon())
                          .build();
    }

    public MainEntity convert(Main main) {
        if (main == null) return null;
        return MainEntity.builder()
                         .grndLevel(main.getGrndLevel())
                         .humidity(main.getHumidity())
                         .pressure(main.getPressure())
                         .seaLevel(main.getSeaLevel())
                         .temperature(main.getTemperature())
                         .temperatureMax(main.getTemperatureMax())
                         .temperatureMin(main.getTemperatureMin())
                         .build();
    }

    public Timestamp convert(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        return Timestamp.from(instant);
    }
}
