package br.edu.utfpr.myfinances.registrations.category.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CategoryUniqueValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryUniqueConstraint {

    String message() default "A categoria com o nome informado já existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
