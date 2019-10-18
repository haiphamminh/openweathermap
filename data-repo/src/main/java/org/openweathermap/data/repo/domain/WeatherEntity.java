package org.openweathermap.data.repo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "weather")
@EqualsAndHashCode(callSuper = true)
public class WeatherEntity extends AuditEntity {
    private Long providerId;
    private String main;
    private String description;
    private String icon;
    @ManyToOne
    @JoinColumn(name = "weather_data_id", nullable = false)
    private WeatherDataEntity weatherData;
}
