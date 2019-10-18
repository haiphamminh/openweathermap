package org.openweathermap.data.collector.service;

import org.openweathermap.data.model.WeatherData;

public interface KafkaProducer {
    void send(String topic, WeatherData weatherData);
}
