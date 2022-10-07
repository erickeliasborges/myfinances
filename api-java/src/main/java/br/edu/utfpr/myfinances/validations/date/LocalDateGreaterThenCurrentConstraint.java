package br.edu.utfpr.myfinances.validations.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Constraint(validatedBy = LocalDateGreaterThenCurrentValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface LocalDateGreaterThenCurrentConstraint {

    String message() default "Data não pode ser inferior a atual.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
