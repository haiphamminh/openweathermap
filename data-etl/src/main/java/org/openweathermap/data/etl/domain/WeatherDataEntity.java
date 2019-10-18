package org.openweathermap.data.etl.domain;

import lombok.Builder;
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
@Builder
@Entity
@Table(name = "weather_data")
@EqualsAndHashCode(callSuper = true)
public class WeatherDataEntity extends AuditEntity {
    private Long cityId;
    private String cityName;
    private Integer timezone;
    private Integer cloudiness;
    @Column(name = "dct")
    private Timestamp dataCalculationTime;
    @OneToOne(cascade = CascadeType.ALL)
    private CoordEntity coord;
    @OneToOne(cascade = CascadeType.ALL)
    private MainEntity main;
    @OneToOne(cascade = CascadeType.ALL)
    private WindEntity wind;
    @OneToOne(cascade = CascadeType.ALL)
    private RainEntity rain;
    @OneToOne(cascade = CascadeType.ALL)
    private SnowEntity snow;
    @OneToOne(cascade = CascadeType.ALL)
    private SysEntity sys;
    @OneToMany(mappedBy = "weatherData")
    private Set<WeatherEntity> weathers;
}
