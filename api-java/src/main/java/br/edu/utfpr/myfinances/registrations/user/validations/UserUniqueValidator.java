package br.edu.utfpr.myfinances.registrations.user.validations;

import br.edu.utfpr.myfinances.registrations.user.User;
import br.edu.utfpr.myfinances.registrations.user.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public record UserUniqueValidator(UserRepository userRepository) implements ConstraintValidator<UserUniqueConstraint, User> {

    private static final String messageConstraint = "O %s informado já está sendo utilizado por outro cadastro. Por favor, informe outro.";

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        Boolean usernameValid = userValid(user,
                Optional.ofNullable(userRepository.findByUsername(user.getUsername())),
                constraintValidatorContext,
                String.format(messageConstraint, "Usuário"),
                "username"
        );

        Boolean emailValid = userValid(user,
                Optional.ofNullable(userRepository.findByEmail(user.getEmail())),
                constraintValidatorContext,
                String.format(messageConstraint, "E-mail"),
                "email"
        );


        return (usernameValid && emailValid);

    }

    private Boolean userValid(User user, Optional<User> userOptional, ConstraintValidatorContext constraintValidatorContext, String messageConstraint, String fieldName) {
        if ((userOptional.isPresent()) && (!Objects.equals(userOptional.get().getId(), user.getId()))) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageConstraint)
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
