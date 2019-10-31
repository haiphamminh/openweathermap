package org.openweathermap.data.repo.spec;

import static org.openweathermap.data.repo.spec.SpecificationUtils.toPredicate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.openweathermap.data.repo.domain.WeatherDataEntity;
import org.springframework.data.jpa.domain.Specification;

@Data
public class SpecificationBuilder {

    private final List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public static Matcher parse(String search) {
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>|>=|<=)([\\w ]+?),", Pattern.UNICODE_CHARACTER_CLASS);
        return pattern.matcher(search + ",");
    }

    public static Specification<WeatherDataEntity> from(String search) {
        Matcher matcher = parse(search);
        SpecificationBuilder builder = new SpecificationBuilder();
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }

    public SpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<WeatherDataEntity> build() {
        if (params.isEmpty()) {
            return null;
        }

        Specification<WeatherDataEntity> result = toPredicate(params.get(0));
        for (int iter = 1; iter < params.size(); iter++) {
            result = Specification.where(result)
                                  .and(toPredicate(params.get(iter)));
        }
        return result;
    }

    @Data
    @AllArgsConstructor
    public static class SearchCriteria {
        private String key;
        private String operation;
        private Object value;
    }
}
