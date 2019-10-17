package org.openweathermap.data.etl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "coord")
@EqualsAndHashCode(callSuper = true)
public class Coord extends BaseEntity {
    private Float lon;
    private Float lat;
    @OneToOne(mappedBy = "coord")
    private WeatherData weatherData;
}
