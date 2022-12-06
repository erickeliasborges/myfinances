package br.edu.utfpr.myfinances.registrations.account.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AccountUniqueValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccountUniqueConstraint {

    String message() default "A conta informada já existe.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
