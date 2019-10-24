package org.openweathermap.data.repo.spec;

import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;

public class DataReporterSpec {
    public static Specification<WeatherDataEntity> inRange(Timestamp startDate, Timestamp endDate) {
        return (Specification<WeatherDataEntity>) (root, query, cb) -> cb.between(root.get("dataCalculationTime"),
                                                                                  startDate, endDate);
    }

    /*public static Specification<WeatherDataEntity> groupBy(List<String> fields) {
        return (Specification<WeatherDataEntity>) (root, query, cb) -> {
            root.join()
        }
    }*/

    /*public static Specification<WeatherDataEntity> inWeek(Timestamp startDate) {
        return (Specification<WeatherDataEntity>) (root, query, cb) -> cb.between(root.get("dataCalculationTime"),
                                                                                  startDate, )
    }*/
/*
    public static Specification<WeatherDataEntity> fields(List<String> fields) {

        return (Specification<WeatherDataEntity>) (root, query, cb) -> {
            final List<Selection<?>> selections = fields.stream()
                                                        .map(root::get)
                                                        .collect(toList());
            query.multiselect(selections);
        }
    }*/
}
