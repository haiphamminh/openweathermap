package org.openweathermap.data.report.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.model.AggregatedData;

@Mapper(componentModel = "spring")
public interface WeatherDataMapper {
    WeatherDataMapper INSTANCE = Mappers.getMapper(WeatherDataMapper.class);

    @Mappings({
                      @Mapping(target = "lon", source = "coord.lon"),
                      @Mapping(target = "lat", source = "coord.lat"),
                      @Mapping(target = "grndLevel", source = "main.grndLevel"),
                      @Mapping(target = "humidity", source = "main.humidity"),
                      @Mapping(target = "pressure", source = "main.pressure"),
                      @Mapping(target = "seaLevel", source = "main.seaLevel"),
                      @Mapping(target = "temperature", source = "main.temperature"),
                      @Mapping(target = "temperatureMax", source = "main.temperatureMax"),
                      @Mapping(target = "temperatureMin", source = "main.temperatureMin"),
                      @Mapping(target = "rainOneHour", source = "rain.rainOneHour"),
                      @Mapping(target = "rainThreeHours", source = "rain.rainThreeHours"),
                      @Mapping(target = "snowOneHour", source = "snow.snowOneHour"),
                      @Mapping(target = "snowThreeHours", source = "snow.snowThreeHours"),
                      @Mapping(target = "country", source = "sys.country"),
                      @Mapping(target = "sunrise", source = "sys.sunrise"),
                      @Mapping(target = "sunset", source = "sys.sunset"),
                      @Mapping(target = "degree", source = "wind.degree"),
                      @Mapping(target = "gust", source = "wind.gust"),
                      @Mapping(target = "speed", source = "wind.speed")
              })
    AggregatedData entityToAggregateData(WeatherDataEntity entity);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
    List<AggregatedData> entitiesToAggregateDataList(List<WeatherDataEntity> entities);
}
