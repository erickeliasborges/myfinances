package br.edu.utfpr.myfinances.registrations.user.validations;

import br.edu.utfpr.myfinances.filters.handlers.ServletFilterHandler;
import br.edu.utfpr.myfinances.registrations.user.UserRepository;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public record UserUniqueValidator(UserRepository userRepository,
                                  ServletFilterHandler servletFilterHandler) implements ConstraintValidator<UserUniqueConstraint, String> {

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return ((updatingUser()) ||
                (!Optional.ofNullable(userRepository.findByUsername(username)).isPresent()));
    }

    private Boolean updatingUser() {
        return ((HttpServletRequest) servletFilterHandler.getServletRequest()).getMethod().equals(HttpMethod.PUT.name());
    }

}
