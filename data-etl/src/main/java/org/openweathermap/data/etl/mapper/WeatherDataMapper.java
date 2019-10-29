package org.openweathermap.data.etl.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.openweathermap.data.model.WeatherData;
import org.openweathermap.data.model.WeatherData.Coord;
import org.openweathermap.data.model.WeatherData.Main;
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

//@Mapper(componentModel = "spring")
public interface WeatherDataMapper {
    @Mappings({
                      @Mapping(target = "cityId", source = "id"),
                      @Mapping(target = "cityName", source = "name"),
                      @Mapping(target = "cloudiness", source = "clouds.all"),
                      @Mapping(target = "timezone", source = "timezone"),
                      @Mapping(target = "weathers", source = "weather"),
              })
    WeatherDataEntity toWeatherDateEntity(WeatherData data);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<WeatherDataEntity> toWeatherDataEntities(List<WeatherData> data);

    CoordEntity toCoordEntity(Coord coord);

    MainEntity toMainEntity(Main main);

    RainEntity toRainEntity(Rain rain);

    SnowEntity toSnowEntity(Snow snow);

    SysEntity toSysEntity(Sys sys);

    WindEntity toWindEntity(Wind wind);

//    WeatherEntity toWeatherEntity(Weather weather);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<WeatherEntity> toWeatherEntities(List<Weather> weathers);

    default WeatherEntity toWeatherEntity(WeatherDataEntity weatherDataEntity, Weather weather) {
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
}
