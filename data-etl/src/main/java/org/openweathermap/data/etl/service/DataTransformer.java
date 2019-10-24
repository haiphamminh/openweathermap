package org.openweathermap.data.etl.service;

import org.openweathermap.data.model.WeatherData;

import java.util.List;

public interface DataTransformer {
    void transformAndSave(List<WeatherData> weatherDatas);
}
