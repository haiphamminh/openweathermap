package org.openweathermap.data.report.service.impl;

import static org.openweathermap.data.repo.spec.SpecificationBuilder.from;
import static org.openweathermap.data.repo.spec.SpecificationUtils.inDateRange;
import static org.springframework.data.jpa.domain.Specification.where;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.repo.WeatherDataRepository;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.report.mapper.WeatherDataMapper;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.model.Fields;
import org.openweathermap.data.report.service.DataReporter;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataReporterImpl implements DataReporter {
    private final WeatherDataRepository repository;
    private final ExcelService excelService;
    private final WeatherDataMapper mapper;

    @Override
    public byte[] generateReport(LocalDateTime startDate, LocalDateTime endDate, List<String> fields, String search,
                                 Pageable pageable)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        Specification<WeatherDataEntity> spec = where(from(search)).and(
                inDateRange(Fields.DCT.getKey(), startDate, endDate));
        Page<WeatherDataEntity> page = repository.findAll(spec, pageable);
        List<AggregatedData> aggregatedData = mapper.entitiesToAggregateDataList(page.getContent());
        return excelService.export(aggregatedData, fields);
    }
}
