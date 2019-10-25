package org.openweathermap.data.report.service.impl;

import static org.openweathermap.data.repo.spec.SpecificationUtils.inRange;
import static org.openweathermap.data.repo.spec.SpecificationBuilder.from;
import static org.springframework.data.jpa.domain.Specification.where;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.openweathermap.data.repo.WeatherDataRepository;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.mapper.WeatherDataMapper;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.model.Header;
import org.openweathermap.data.report.service.DataReporter;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
public class DataReporterImpl implements DataReporter {
    private final WeatherDataRepository repository;
    private final ExcelService excelService;
    private final WeatherDataMapper mapper;

    private static void validateFields(List<String> fields) {
        if (CollectionUtils.isEmpty(fields)) {
            return;
        }
        List<String> keys = Header.getHeaderKeys();
        String invalidFields = fields.stream()
                                     .filter(f -> !keys.contains(f))
                                     .collect(Collectors.joining(","));
        if (!invalidFields.isEmpty()) {
            throw new IllegalArgumentException("The following fields are not supported: " + invalidFields);
        }
    }

    @Override
    public byte[] generateReport(Timestamp startDate, Timestamp endDate, List<String> fields, String search)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        validateFields(fields);
        Specification<WeatherDataEntity> spec = from(search);
        List<WeatherDataEntity> data = repository.findAll(where(spec).and(inRange(startDate, endDate)));
        List<AggregatedData> aggregatedData = mapper.entitiesToAggregateDataList(data);
        return excelService.export(aggregatedData, fields);
    }
}
