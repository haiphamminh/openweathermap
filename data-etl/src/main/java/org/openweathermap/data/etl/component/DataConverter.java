package org.openweathermap.data.etl.component;

import static org.openweathermap.data.model.WeatherData.Main;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.openweathermap.data.model.WeatherData;
import org.openweathermap.data.model.WeatherData.Coord;
import org.openweathermap.data.model.WeatherData.Rain;
import org.openweathermap.data.model.WeatherData.Snow;
import org.openweathermap.data.model.WeatherData.Sys;
import org.openweathermap.data.model.WeatherData.Weather;
import org.openweathermap.data.model.WeatherData.Wind;
import org.openweathermap.data.repo.domain.CoordEntity;
import org.openweathermap.data.repo.domain.MainEntity;
import org.openweathermap.data.repo.domain.RainEntity;
import org.openweathermap.data.repo.domain.SnowEntity;
import org.openweathermap.data.repo.domain.SysEntity;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.repo.domain.WeatherEntity;
import org.openweathermap.data.repo.domain.WindEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

    public WeatherDataEntity convert(WeatherData data) {
        WeatherDataEntity weatherDataEntity = WeatherDataEntity.builder()
                                                               .cityId(data.getId())
                                                               .cityName(data.getName())
                                                               .cloudiness(data.getClouds()
                                                                               .getAll())
                                                               .timezone(data.getTimezone())
                                                               .dataCalculationTime(convert(data.getDt() * 1000))
                                                               .coord(convert(data.getCoord()))
                                                               .main(convert(data.getMain()))
                                                               .rain(convert(data.getRain()))
                                                               .snow(convert(data.getSnow()))
                                                               .sys(convert(data.getSys()))
                                                               .wind(convert(data.getWind()))
                                                               .build();
        weatherDataEntity.setWeathers(convert(weatherDataEntity, data.getWeather()));
        return weatherDataEntity;
    }

    public List<WeatherEntity> convert(WeatherDataEntity weatherDataEntity, List<Weather> weathers) {
        if (CollectionUtils.isEmpty(weathers)) {
            return Collections.emptyList();
        }
        return weathers.stream()
                       .map(weather -> convert(weatherDataEntity, weather))
                       .collect(Collectors.toList());
    }

    public WeatherEntity convert(WeatherDataEntity weatherDataEntity, Weather weather) {
        if (weather == null) {
            return null;
        }

        return WeatherEntity.builder()
                            .description(weather.getDescription())
                            .icon(weather.getIcon())
                            .status(weather.getMain())
                            .providerId(weather.getId())
                            .weatherData(weatherDataEntity)
                            .build();
    }

    public WindEntity convert(Wind wind) {
        if (wind == null) {
            return null;
        }
        return WindEntity.builder()
                         .degree(wind.getDegree())
                         .gust(wind.getGust())
                         .speed(wind.getSpeed())
                         .build();
    }

    public SysEntity convert(Sys sys) {
        if (sys == null) {
            return null;
        }
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
                         .snowOneHour(snow.getOneHour())
                         .snowThreeHours(snow.getThreeHours())
                         .build();
    }

    public RainEntity convert(Rain rain) {
        if (rain == null) {
            return null;
        }
        return RainEntity.builder()
                         .rainOneHour(rain.getOneHour())
                         .rainThreeHours(rain.getThreeHours())
                         .build();
    }

    public CoordEntity convert(Coord coord) {
        if (coord == null) {
            return null;
        }
        return CoordEntity.builder()
                          .lat(coord.getLat())
                          .lon(coord.getLon())
                          .build();
    }

    public MainEntity convert(Main main) {
        if (main == null) {
            return null;
        }
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
