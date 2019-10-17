package org.openweathermap.data.etl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "snow")
@EqualsAndHashCode(callSuper = true)
public class Snow extends BaseEntity {
    @Column(name = "one_hour")
    private Float oneHour;
    @Column(name = "three_hours")
    private Float threeHours;
    @OneToOne(mappedBy = "snow")
    private WeatherData weatherData;
}
