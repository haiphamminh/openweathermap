package org.openweathermap.data.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "weather")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WeatherEntity extends AuditEntity {
    private Long providerId;
    @Column(name = "main")
    private String status;
    private String description;
    private String icon;
    @ManyToOne
    @JoinColumn(name = "weather_data_id", nullable = false)
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
