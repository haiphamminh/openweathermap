package org.openweathermap.data.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "main")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MainEntity extends AuditEntity {
    @Column(name = "temp")
    private Float temperature;
    private Float pressure;
    private Integer humidity;
    @Column(name = "temp_min")
    private Float temperatureMin;
    @Column(name = "temp_max")
    private Float temperatureMax;
    @Column(name = "sea_level")
    private Float seaLevel;
    @Column(name = "grnd_level")
    private Float grndLevel;
    @OneToOne(mappedBy = "main")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
