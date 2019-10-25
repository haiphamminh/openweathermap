package org.openweathermap.data.report.service;

import java.io.IOException;
import java.util.List;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.model.AggregatedData;
import org.springframework.data.domain.Page;

public interface ExcelService {
    byte[] export(List<AggregatedData> data, List<String> fields)
            throws IOException, NoSuchFieldException, IllegalAccessException;
}
