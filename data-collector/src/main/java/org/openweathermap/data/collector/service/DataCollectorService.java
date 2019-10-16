package org.openweathermap.data.collector.service;

import org.openweathermap.data.collector.model.WeatherData;

public interface DataCollectorService {
    WeatherData fetchCurrentWeatherDataByCityName(String query, String units, String mode);

    WeatherData fetchCurrentWeatherDataByCityID(long cityID, String units, String mode);

    WeatherData fetchCurrentWeatherDataByLocation(float lon, float lat, String units, String mode);
}
