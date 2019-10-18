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
@Table(name = "snow")
@EqualsAndHashCode(callSuper = true)
public class SnowEntity extends AuditEntity {
    @Column(name = "one_hour")
    private Float oneHour;
    @Column(name = "three_hours")
    private Float threeHours;
    @OneToOne(mappedBy = "snow")
    private WeatherDataEntity weatherData;
}
