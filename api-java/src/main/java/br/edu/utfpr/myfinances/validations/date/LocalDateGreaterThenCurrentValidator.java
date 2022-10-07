package br.edu.utfpr.myfinances.validations.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class LocalDateGreaterThenCurrentValidator implements ConstraintValidator<LocalDateGreaterThenCurrentConstraint, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime localDateTime, ConstraintValidatorContext constraintValidatorContext) {
        return (localDateTime.isAfter(LocalDateTime.now()));
    }

}
