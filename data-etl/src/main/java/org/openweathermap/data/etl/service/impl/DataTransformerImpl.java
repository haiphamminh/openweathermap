package org.openweathermap.data.etl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openweathermap.data.etl.component.DataConverter;
import org.openweathermap.data.etl.service.DataTransformer;
import org.openweathermap.data.model.WeatherData;
import org.openweathermap.data.repo.BaseRepository;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DataTransformerImpl implements DataTransformer {
    private final BaseRepository repository;
    private final DataConverter dataConverter;

    @Override
    public void transformAndSave(List<WeatherData> data) {
        List<WeatherDataEntity> entities = dataConverter.convert(data);
        repository.saveAll(entities);
        log.info("Save {} entities successfully", entities.size());
    }
}
