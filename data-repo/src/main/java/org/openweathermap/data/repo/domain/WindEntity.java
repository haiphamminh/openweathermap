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
@Table(name = "wind")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WindEntity extends AuditEntity {
    private Float speed;
    @Column(name = "deg")
    private Float degree;
    private Float gust;
    @OneToOne(mappedBy = "wind")
    @JsonIgnore
    private WeatherDataEntity weatherData;
}
