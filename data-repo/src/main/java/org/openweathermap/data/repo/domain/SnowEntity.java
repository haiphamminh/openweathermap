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
@Table(name = "snow")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SnowEntity extends AuditEntity {
    @Column(name = "one_hour")
    private Float snowOneHour;
    @Column(name = "three_hours")
    private Float snowThreeHours;
    @OneToOne(mappedBy = "snow")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
