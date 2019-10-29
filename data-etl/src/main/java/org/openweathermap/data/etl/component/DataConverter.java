package org.openweathermap.data.etl.component;

import static org.openweathermap.data.model.WeatherData.Main;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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
        System.out.println(converter.formatAtTimezone(1571383815, ZoneOffset.ofTotalSeconds(25200)));
        ZonedDateTime instant = Instant.ofEpochSecond(1571383815)
                                       .atZone(ZoneOffset.UTC);
        System.out.println(instant.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z")));
        System.out.println(instant);
        /*Timestamp t = converter.convert(1571383815, ZoneOffset.UTC);
        DateFormat df = DateFormat.getDateTimeInstance();
        df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(df.format(t));
        System.out.println(t);*/
    }

    public List<WeatherDataEntity> toEntity(List<WeatherData> data) {
        return data.stream()
                   .map(this::toEntity)
                   .collect(Collectors.toList());
    }

    public WeatherDataEntity toEntity(WeatherData data) {
        WeatherDataEntity weatherDataEntity = WeatherDataEntity.builder()
                                                               .cityId(data.getId())
                                                               .cityName(data.getName())
                                                               .cloudiness(data.getClouds()
                                                                               .getAll())
                                                               .timezone(data.getTimezone())
                                                               .dct(atOffset(data.getDt(),
                                                                             ZoneOffset.UTC).toLocalDateTime())
                                                               .coord(toEntity(data.getCoord()))
                                                               .main(toEntity(data.getMain()))
                                                               .rain(toEntity(data.getRain()))
                                                               .snow(toEntity(data.getSnow()))
                                                               .sys(toEntity(data.getSys(), data.getTimezone()))
                                                               .wind(toEntity(data.getWind()))
                                                               .build();
        weatherDataEntity.setWeathers(toEntity(weatherDataEntity, data.getWeather()));
        return weatherDataEntity;
    }

    public List<WeatherEntity> toEntity(WeatherDataEntity weatherDataEntity, List<Weather> weathers) {
        if (CollectionUtils.isEmpty(weathers)) {
            return Collections.emptyList();
        }
        return weathers.stream()
                       .map(weather -> toEntity(weatherDataEntity, weather))
                       .collect(Collectors.toList());
    }

    public WeatherEntity toEntity(WeatherDataEntity weatherDataEntity, Weather weather) {
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

    public WindEntity toEntity(Wind wind) {
        if (wind == null) {
            return null;
        }
        return WindEntity.builder()
                         .degree(wind.getDegree())
                         .gust(wind.getGust())
                         .speed(wind.getSpeed())
                         .build();
    }

    public SysEntity toEntity(Sys sys, Integer timezone) {
        if (sys == null) {
            return null;
        }
        ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezone);
        return SysEntity.builder()
                        .country(sys.getCountry())
                        .originalSunrise(atOffset(sys.getSunrise(), ZoneOffset.UTC).toLocalDateTime())
                        .originalSunset(atOffset(sys.getSunset(), ZoneOffset.UTC).toLocalDateTime())
                        .sunrise(formatAtTimezone(sys.getSunrise(), zoneOffset))
                        .sunset(formatAtTimezone(sys.getSunset(), zoneOffset))
                        .build();
    }

    public SnowEntity toEntity(Snow snow) {
        if (snow == null) {
            return null;
        }
        return SnowEntity.builder()
                         .snowOneHour(snow.getSnowOneHour())
                         .snowThreeHours(snow.getSnowThreeHours())
                         .build();
    }

    public RainEntity toEntity(Rain rain) {
        if (rain == null) {
            return null;
        }
        return RainEntity.builder()
                         .rainOneHour(rain.getRainOneHour())
                         .rainThreeHours(rain.getRainThreeHours())
                         .build();
    }

    public CoordEntity toEntity(Coord coord) {
        if (coord == null) {
            return null;
        }
        return CoordEntity.builder()
                          .lat(coord.getLat())
                          .lon(coord.getLon())
                          .build();
    }

    public MainEntity toEntity(Main main) {
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

    public ZonedDateTime atOffset(long timeInSec, ZoneOffset zoneOffset) {
        return Instant.ofEpochSecond(timeInSec)
                      .atZone(zoneOffset);
    }

    public String formatAtTimezone(long timeInSec, ZoneOffset zoneOffset) {
        ZonedDateTime zonedDateTime = atOffset(timeInSec, zoneOffset);
        return zonedDateTime.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss zzzz"));
    }
}
