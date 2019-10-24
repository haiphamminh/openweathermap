package org.openweathermap.data.report.service.impl;

import lombok.RequiredArgsConstructor;
import org.openweathermap.data.repo.BaseRepository;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.repo.spec.DataReporterSpec;
import org.openweathermap.data.report.mapper.DataMapper;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.service.DataReporter;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@RequiredArgsConstructor
public class DataReporterImpl implements DataReporter {
    private final BaseRepository repository;
    private final ExcelService excelService;
    private final DataMapper mapper;

    @Override
    public byte[] generate(Timestamp startDate,
                           Timestamp endDate) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<WeatherDataEntity> data;
        if (startDate == null || endDate == null) {
            data = repository.findAll();
        } else {
            data = repository.findAll(where(DataReporterSpec.inRange(startDate, endDate)));
        }

        List<AggregatedData> aggregatedData = mapper.map(data);
        return excelService.export("report.xls", aggregatedData);
    }

    @Override
    public List<WeatherDataEntity> search() {
        return repository.search();
    }
}
