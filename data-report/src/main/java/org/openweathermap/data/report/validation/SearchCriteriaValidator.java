package org.openweathermap.data.report.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import org.openweathermap.data.repo.spec.SpecificationBuilder;

public class SearchCriteriaValidator extends AbstractFieldValidator<String> {
    @Override
    protected List<String> extractFields(String search) {
        Matcher matcher = SpecificationBuilder.parse(search);
        List<String> fields = new ArrayList<>();
        while (matcher.find()) {
            fields.add(matcher.group(1));
        }
        return fields;
    }
}
