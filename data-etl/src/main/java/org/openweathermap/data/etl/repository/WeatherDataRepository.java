package org.openweathermap.data.etl.repository;

import org.openweathermap.data.etl.domain.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
}
