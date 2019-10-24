package org.openweathermap.data.report.service;

import org.openweathermap.data.repo.domain.WeatherDataEntity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public interface DataReporter {
    byte[] generate(Timestamp startDate,
                    Timestamp endDate) throws IOException, NoSuchFieldException, IllegalAccessException;

    List<WeatherDataEntity> search();
}
