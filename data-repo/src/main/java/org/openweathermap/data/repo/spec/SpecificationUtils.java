package org.openweathermap.data.repo.spec;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.openweathermap.data.repo.spec.SpecificationBuilder.SearchCriteria;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class SpecificationUtils {
    private static final Map<String, String> joinMap;

    static {
        joinMap = new HashMap<>();
        Field[] fields = WeatherDataEntity.class.getDeclaredFields();
        Stream.of(fields)
              .filter(f -> !BeanUtils.isSimpleValueType(f.getType()))
              .forEach(f -> Stream.of(f.getType()
                                       .getDeclaredFields())
                                  .filter(childField -> BeanUtils.isSimpleValueType(childField.getType()))
                                  .forEach(childField -> {
                                      joinMap.put(childField.getName(), f.getName());
                                  }));
    }

    public static Specification<WeatherDataEntity> inRange(Timestamp startDate, Timestamp endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        return (Specification<WeatherDataEntity>) (root, query, cb) -> cb
                .between(root.get("dataCalculationTime"),
                         startDate, endDate);
    }

    public static Specification<WeatherDataEntity> toPredicate(SearchCriteria criteria) {
        return (Specification<WeatherDataEntity>) (root, query, cb) -> {
            String key = criteria.getKey();
            String operation = criteria.getOperation();
            Object value = criteria.getValue();
            String joinedAttr = joinMap.get(key);
            Path<String> path =
                    StringUtils.isEmpty(joinedAttr) ? root.get(key) : root.join(joinedAttr, JoinType.LEFT)
                                                                          .get(key);
            switch (operation) {
                case ">":
                    return cb.greaterThan(path, value.toString());
                case ">=":
                    return cb.greaterThanOrEqualTo(path, value.toString());
                case "<":
                    return cb.lessThan(path, value.toString());
                case "<=":
                    return cb.lessThanOrEqualTo(path, value.toString());
                case ":": {
                    if (path.getJavaType() == String.class) {
                        return cb.like(path, "%" + value.toString() + "%");
                    }
                    return cb.equal(path, value);
                }
                default:
                    return null;
            }
        };
    }
}
