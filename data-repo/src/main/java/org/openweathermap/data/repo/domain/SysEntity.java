package org.openweathermap.data.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.Basic;
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
@Table(name = "sys")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysEntity extends AuditEntity {
    private String country;
    @Basic
    private LocalDateTime originalSunrise;
    @Basic
    private LocalDateTime originalSunset;
    private String sunrise;
    private String sunset;
    @OneToOne(mappedBy = "sys")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
