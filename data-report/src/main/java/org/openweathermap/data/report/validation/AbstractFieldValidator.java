package org.openweathermap.data.report.validation;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.openweathermap.data.report.model.Fields;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public abstract class AbstractFieldValidator<T> implements ConstraintValidator<FieldConstraint, T> {
    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
        List<String> fields = extractFields(value);
        if (CollectionUtils.isEmpty(fields)) {
            return true;
        }
        List<String> keys = Fields.getHeaderKeys();
        String invalidFields = fields.stream()
                                     .filter(f -> !keys.contains(f))
                                     .collect(Collectors.joining(", "));
        if (StringUtils.isEmpty(invalidFields)) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(invalidFields + " not supported")
               .addConstraintViolation();
        return false;
    }

    protected abstract List<String> extractFields(T t);
}
