package org.openweathermap.data.repo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Builder
@Entity
@Table(name = "sys")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysEntity extends AuditEntity {
    private String country;
    private Timestamp sunrise;
    private Timestamp sunset;
    @OneToOne(mappedBy = "sys")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
