package org.openweathermap.data.repo;

import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, Long> {
}
