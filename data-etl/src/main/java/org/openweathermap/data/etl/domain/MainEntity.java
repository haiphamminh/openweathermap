package org.openweathermap.data.etl.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "main")
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
    private WeatherDataEntity weatherData;
}
