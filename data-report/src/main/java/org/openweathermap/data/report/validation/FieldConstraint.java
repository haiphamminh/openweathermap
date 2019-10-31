package org.openweathermap.data.report.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {FieldsValidator.class, SearchCriteriaValidator.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
                ElementType.PARAMETER, ElementType.TYPE_USE})
public @interface FieldConstraint {
    String message() default "Some fields are not supported";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
