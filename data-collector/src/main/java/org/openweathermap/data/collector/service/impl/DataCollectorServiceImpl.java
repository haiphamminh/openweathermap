package org.openweathermap.data.collector.service.impl;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.collector.config.WeatherMapSettings;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.openweathermap.data.collector.service.KafkaProducer;
import org.openweathermap.data.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataCollectorServiceImpl implements DataCollectorService {
    private final KafkaProducer kafkaProducer;
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

        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
        log.info("Fetched the current weather data");
        if (weatherData != null) {
            kafkaProducer.send("weather.data", weatherData);
            log.info("Sent the data to Kafka successfully");
        }
        return weatherData;
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
