package org.openweathermap.data.report.validation;

import java.util.List;

public class FieldsValidator extends AbstractFieldValidator<List<String>> {
    @Override
    protected List<String> extractFields(List<String> fields) {
        return fields;
    }
}
