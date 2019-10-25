package org.openweathermap.data.repo;

import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeatherDataRepository extends JpaRepository<WeatherDataEntity, Long>,
        JpaSpecificationExecutor<WeatherDataEntity> {
}
