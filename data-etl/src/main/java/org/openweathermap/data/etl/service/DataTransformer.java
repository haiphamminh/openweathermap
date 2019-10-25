package org.openweathermap.data.etl.service;

import java.util.List;
import org.openweathermap.data.model.WeatherData;

public interface DataTransformer {
    void transformAndSave(List<WeatherData> weatherDatas);
}
