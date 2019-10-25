package org.openweathermap.data.collector.scheduling;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.collector.model.City;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataCollectorScheduler {
    private static Set<City> cities;

    static {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new File(DataCollectorScheduler.class.getResource("/data/current.city.list.json")
                                                             .getFile());
            cities = mapper.readValue(file, new TypeReference<Set<City>>() {
            });
        } catch (Exception e) {
            log.error("Failed to read the list of cities", e);
        }
    }

    private final DataCollectorService dataCollectorService;

    @Scheduled(fixedRate = 1 * 60 * 60 * 1000)
//    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void collectData() {
        if (CollectionUtils.isEmpty(cities)) {
            return;
        }
        cities.forEach(city -> {
            try {
                String name = city.getName() + "," + city.getCountry();
                dataCollectorService.fetchCurrentWeatherDataByCityName(name, "metrics", "");
            } catch (Exception e) {
                log.error("Failed to fetch data for " + city, e);
            }
        });
    }
}
