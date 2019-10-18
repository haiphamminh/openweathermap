package org.openweathermap.data.etl.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.model.WeatherData;
import org.openweathermap.data.etl.service.DataTransformer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final DataTransformer dataTransformer;

    @KafkaListener(id = "batch-listener", topics = "weather.data")
    public void consume(@Payload List<WeatherData> data,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        System.out.println("data size: " + data.size());
        dataTransformer.transformAndSave(data);
    }
}
