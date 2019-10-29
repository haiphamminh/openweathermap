package org.openweathermap.data.repo.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@Entity
@Table(name = "weather_data")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = {"cityId", "cityName"})
@ToString(callSuper = true, of = {"cityId", "cityName", "timezone", "cloudiness", "dct"})
public class WeatherDataEntity extends AuditEntity {
    private Long cityId;
    private String cityName;
    private Integer timezone;
    private Integer cloudiness;
    @Column(name = "dct")
    private LocalDateTime dct;
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
    @OneToMany(mappedBy = "weatherData", cascade = CascadeType.ALL)
    private List<WeatherEntity> weathers;
}
