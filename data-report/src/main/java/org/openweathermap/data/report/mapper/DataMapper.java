package org.openweathermap.data.report.mapper;

import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.model.AggregatedData;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class DataMapper {
    public List<AggregatedData> map(List<WeatherDataEntity> data) {
        return data.stream()
                   .map(this::map)
                   .collect(toList());
    }

    public AggregatedData map(WeatherDataEntity e) {
        AggregatedData aggregatedData = AggregatedData.builder()
                                                      .cityId(e.getCityId())
                                                      .cityName(e.getCityName())
                                                      .cloudiness(e.getCloudiness())

                                                      .dataCalculationTime(e.getDataCalculationTime()
                                                                            .toString())
                                                      .timezone(e.getTimezone())
//                                  .weatherDescription(e.getWeathers().)
                                                      .build();
        if (e.getSys() != null) {
            aggregatedData.setCountry(e.getSys()
                                       .getCountry());
            aggregatedData.setSunrise(e.getSys()
                                       .getSunrise()
                                       .toString());
            aggregatedData.setSunset(e.getSys()
                                      .getSunrise()
                                      .toString());
        }
        if (e.getWind() != null) {
            aggregatedData.setDegree(e.getWind()
                                      .getDegree());
            aggregatedData.setGrndLevel(e.getMain()
                                         .getGrndLevel());
            aggregatedData.setGust(e.getWind()
                                    .getGust());
            aggregatedData.setSpeed(e.getWind()
                                     .getSpeed());
        }
        if (e.getMain() != null) {
            aggregatedData.setHumidity(e.getMain()
                                        .getHumidity());
            aggregatedData.setPressure(e.getMain()
                                        .getPressure());
            aggregatedData.setSeaLevel(e.getMain()
                                        .getSeaLevel());
            aggregatedData.setTemperature(e.getMain()
                                           .getTemperature());
            aggregatedData.setTemperatureMax(e.getMain()
                                              .getTemperatureMax());
            aggregatedData.setTemperatureMin(e.getMain()
                                              .getTemperatureMin());
        }
        if (e.getCoord() != null) {
            aggregatedData.setLat(e.getCoord()
                                   .getLat());
            aggregatedData.setLon(e.getCoord()
                                   .getLon());
        }
        if (e.getRain() != null) {
            aggregatedData.setRainOneHour(e.getRain()
                                           .getOneHour());
            aggregatedData.setRainThreeHours(e.getRain()
                                              .getThreeHours());
        }
        if (e.getSnow() != null) {
            aggregatedData.setRainOneHour(e.getSnow()
                                           .getOneHour());
            aggregatedData.setRainThreeHours(e.getSnow()
                                              .getThreeHours());
        }
        return aggregatedData;
    }
}
