package org.openweathermap.data.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "rain")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RainEntity extends AuditEntity {
    @Column(name = "one_hour")
    private Float rainOneHour;
    @Column(name = "three_hours")
    private Float rainThreeHours;
    @OneToOne(mappedBy = "rain")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
