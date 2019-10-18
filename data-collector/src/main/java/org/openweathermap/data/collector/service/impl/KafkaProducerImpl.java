package org.openweathermap.data.collector.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.service.KafkaProducer;
import org.openweathermap.data.model.WeatherData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {
    private final KafkaTemplate<String, WeatherData> kafkaTemplate;

    @Override
    public void send(String topic, WeatherData weatherData) {
        kafkaTemplate.send(topic, weatherData);
    }
}
