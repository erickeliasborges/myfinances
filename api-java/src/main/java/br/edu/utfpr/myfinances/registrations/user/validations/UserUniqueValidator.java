package br.edu.utfpr.myfinances.registrations.user.validations;

import br.edu.utfpr.myfinances.registrations.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserUniqueValidator implements ConstraintValidator<UserUniqueConstraint, String> {

//    private final UserRepository userRepository;
//
//    public UserUniqueValidator(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
//        if (Objects.isNull(userRepository) || Objects.isNull(servletFilterHandler))
//            return true;

//        return ((updatingUser()) ||
//                (!Optional.ofNullable(userRepository.findByUsername(username)).isPresent()));
        return true;
    }

    private Boolean updatingUser() {
//        return (!Objects.isNull(servletFilterHandler)) &&
//                ((HttpServletRequest) servletFilterHandler.getServletRequest()).getMethod().equals(HttpMethod.PUT.name());
        return false;
    }

}
