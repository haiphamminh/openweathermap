package org.openweathermap.data.collector.scheduling;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.openweathermap.data.collector.service.KafkaProducer;
import org.openweathermap.data.model.WeatherData;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class DataCollectorScheduler {
    private final DataCollectorService dataCollectorService;
    private final KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 1 * 60 * 60 * 1000)
    public void collectData() {
        System.out.println(new Date());
        WeatherData weatherData = dataCollectorService.fetchCurrentWeatherDataByCityName("Thanh pho Ho Chi Minh,vn",
                                                                                         "metrics", "");
        kafkaProducer.send("weather.data", weatherData);
    }
}
