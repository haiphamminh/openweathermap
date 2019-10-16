package org.openweathermap.data.collector.controller;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.collector.service.DataCollectorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data/weather")
@RequiredArgsConstructor
public class DataCollectorController {

    private final DataCollectorService dataCollectorService;

    @PostMapping("/city_name")
    public void fetchCurrentWeatherDataByCityName(@RequestParam("q") String query, @RequestParam(required = false,
                                                                                                 defaultValue =
                                                                                                         "metrics") String units,
                                                  @RequestParam(required = false) String mode) {
        dataCollectorService.fetchCurrentWeatherDataByCityName(query, units, mode);
    }

    @PostMapping("/city_id")
    public void fetchCurrentWeatherDataByCityID(@RequestParam("id") long cityID,
                                                @RequestParam(required = false, defaultValue = "metrics") String units,
                                                @RequestParam(required = false) String mode) {

    }

    @PostMapping("/location")
    public void fetchCurrentWeatherDataByLocation(@RequestParam int lat, @RequestParam int lon,
                                                  @RequestParam(required = false,
                                                                defaultValue = "metrics") String units,
                                                  @RequestParam(required = false) String mode) {

    }
}
