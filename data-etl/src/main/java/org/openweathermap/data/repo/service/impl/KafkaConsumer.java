package org.openweathermap.data.repo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.model.WeatherData;
import org.openweathermap.data.repo.service.DataTransformer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final DataTransformer dataTransformer;

    @KafkaListener(id = "batch-listener", topics = "weather.data")
    public void consume(@Payload List<WeatherData> data,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("Consumed {} messages from Kafka", data.size());
        dataTransformer.transformAndSave(data);
    }
}