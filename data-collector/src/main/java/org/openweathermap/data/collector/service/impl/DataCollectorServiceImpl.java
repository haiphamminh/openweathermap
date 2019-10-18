package org.openweathermap.data.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.config.WeatherMapSettings;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.openweathermap.data.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class DataCollectorServiceImpl implements DataCollectorService {

    private final RestTemplate restTemplate;
    private final WeatherMapSettings settings;

    @Override
    public WeatherData fetchCurrentWeatherDataByCityName(String query, String units, String mode) {
        URI url = UriComponentsBuilder.fromUriString("http://api.openweathermap.org/data/2.5/weather")
                                      .queryParam("appid", "df7974b458bba85b70723ac00aa4ef76")
                                      .queryParam("q", query)
                                      .queryParam("units", units)
                                      .queryParam("mode", mode)
                                      .build()
                                      .encode()
                                      .toUri();

        return restTemplate.getForObject(url, WeatherData.class);
    }

    @Override
    public WeatherData fetchCurrentWeatherDataByCityID(long cityID, String units, String mode) {
        return null;
    }

    @Override
    public WeatherData fetchCurrentWeatherDataByLocation(float lon, float lat, String units, String mode) {
        return null;
    }
}
