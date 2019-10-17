package org.openweathermap.data.etl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "wind")
@EqualsAndHashCode(callSuper = true)
public class Wind extends BaseEntity {
    private Float speed;
    private Float degree;
    private Float gust;
    @OneToOne(mappedBy = "wind")
    private WeatherData weatherData;
}
