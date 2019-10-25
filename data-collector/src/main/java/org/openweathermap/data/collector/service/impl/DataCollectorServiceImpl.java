package org.openweathermap.data.collector.service.impl;

import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.openweathermap.data.collector.service.KafkaProducer;
import org.openweathermap.data.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataCollectorServiceImpl implements DataCollectorService {
    private final KafkaProducer kafkaProducer;
    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.url}")
    private String apiUrl;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @Value("${openweathermap.topic}")
    private String topic;

    @Override
    public WeatherData fetchCurrentWeatherDataByCityName(String query, String units, String mode) {
        URI url = UriComponentsBuilder.fromUriString(apiUrl)
                                      .queryParam("appid", apiKey)
                                      .queryParam("q", query)
                                      .queryParam("units", units)
                                      .queryParam("mode", mode)
                                      .build()
                                      .encode()
                                      .toUri();

        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);
        log.info("Fetched the current weather data");
        if (weatherData != null) {
            kafkaProducer.send(topic, weatherData);
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
