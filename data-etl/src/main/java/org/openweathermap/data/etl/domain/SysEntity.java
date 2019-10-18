package org.openweathermap.data.etl.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@Table(name = "sys")
@EqualsAndHashCode(callSuper = true)
public class SysEntity extends AuditEntity {
    private String country;
    private Timestamp sunrise;
    private Timestamp sunset;
    @OneToOne(mappedBy = "sys")
    private WeatherDataEntity weatherData;
}
