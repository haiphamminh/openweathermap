package org.openweathermap.data.collector.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "openweathermap")
public class WeatherMapSettings {
    private String apiUrl;
    private String apiKey;
}
