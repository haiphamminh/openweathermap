package org.openweathermap.data.collector.scheduling;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataCollectorScheduler {
    private final DataCollectorService dataCollectorService;

    @Scheduled(fixedRate = 1 * 60 * 60 * 1000)
    public void collectData() {
        dataCollectorService.fetchCurrentWeatherDataByCityName("Thanh pho Ho Chi Minh,vn", "metrics", "");
    }
}
