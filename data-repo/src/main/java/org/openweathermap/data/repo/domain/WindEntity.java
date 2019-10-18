package org.openweathermap.data.repo.domain;

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
@Table(name = "wind")
@EqualsAndHashCode(callSuper = true)
public class WindEntity extends AuditEntity {
    private Float speed;
    @Column(name = "deg")
    private Float degree;
    private Float gust;
    @OneToOne(mappedBy = "wind")
    private WeatherDataEntity weatherData;
}
