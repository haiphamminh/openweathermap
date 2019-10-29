package org.openweathermap.data.report.service.impl;

import static org.openweathermap.data.repo.spec.SpecificationBuilder.from;
import static org.openweathermap.data.repo.spec.SpecificationUtils.inDateRange;
import static org.springframework.data.jpa.domain.Specification.where;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.repo.WeatherDataRepository;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.repo.spec.SpecificationBuilder;
import org.openweathermap.data.repo.spec.SpecificationBuilder.SearchCriteria;
import org.openweathermap.data.report.mapper.WeatherDataMapper;
import org.openweathermap.data.report.model.AggregatedData;
import org.openweathermap.data.report.model.Fields;
import org.openweathermap.data.report.service.DataReporter;
import org.openweathermap.data.report.service.ExcelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataReporterImpl implements DataReporter {
    private final WeatherDataRepository repository;
    private final ExcelService excelService;
    private final WeatherDataMapper mapper;

    private static void validateFields(List<String> fields, List<SearchCriteria> criteria) {
        Set<String> allFields = new HashSet<>();
        if (!CollectionUtils.isEmpty(fields)) {
            allFields.addAll(fields);
        }
        if (!CollectionUtils.isEmpty(criteria)) {
            allFields.addAll(criteria.stream()
                                     .map(SearchCriteria::getKey)
                                     .collect(Collectors.toList()));
        }
        List<String> keys = Fields.getHeaderKeys();
        String invalidFields = allFields.stream()
                                        .filter(f -> !keys.contains(f))
                                        .collect(Collectors.joining(","));
        if (!StringUtils.isEmpty(invalidFields)) {
            throw new IllegalArgumentException("The following fields are not supported: " + invalidFields);
        }
    }

    @Override
    public byte[] generateReport(LocalDateTime startDate, LocalDateTime endDate, List<String> fields, String search,
                                 Pageable pageable)
            throws IOException, NoSuchFieldException, IllegalAccessException {
        SpecificationBuilder specBuilder = from(search);
        validateFields(fields, specBuilder.getParams());
        Page<WeatherDataEntity> page = repository.findAll(where(specBuilder.build()).and(
                inDateRange(Fields.DCT.getKey(), startDate, endDate)),
                                                          pageable);
        List<AggregatedData> aggregatedData = mapper.entitiesToAggregateDataList(page.getContent());
        return excelService.export(aggregatedData, fields);
    }
}
