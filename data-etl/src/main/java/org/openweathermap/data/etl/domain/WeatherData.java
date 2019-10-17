package org.openweathermap.data.etl.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "weather_data")
@EqualsAndHashCode(callSuper = true)
public class WeatherData extends BaseEntity {
    private Long providerId;
    private Long cityId;
    private String cityName;
    private Integer timezone;
    private Integer cloudiness;
    @Column(name = "dct")
    private Timestamp dataCalculationTime;
    @OneToOne(cascade = CascadeType.ALL)
    private Coord coord;
    @OneToOne(cascade = CascadeType.ALL)
    private Main main;
    @OneToOne(cascade = CascadeType.ALL)
    private Wind wind;
    @OneToOne(cascade = CascadeType.ALL)
    private Rain rain;
    @OneToOne(cascade = CascadeType.ALL)
    private Snow snow;
    @OneToOne(cascade = CascadeType.ALL)
    private Sys sys;
    @OneToMany(mappedBy = "weatherData")
    private Set<Weather> weather;
}
