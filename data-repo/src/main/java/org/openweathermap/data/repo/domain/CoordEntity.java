package org.openweathermap.data.repo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "coord")
@EqualsAndHashCode(callSuper = true)
public class CoordEntity extends AuditEntity {
    private Float lon;
    private Float lat;
    @OneToOne(mappedBy = "coord")
    private WeatherDataEntity weatherData;
}
