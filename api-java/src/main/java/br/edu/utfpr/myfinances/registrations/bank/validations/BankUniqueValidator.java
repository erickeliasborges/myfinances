package br.edu.utfpr.myfinances.registrations.bank.validations;

import br.edu.utfpr.myfinances.registrations.bank.Bank;
import br.edu.utfpr.myfinances.registrations.bank.BankRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public record BankUniqueValidator(
        BankRepository bankRepository) implements ConstraintValidator<BankUniqueConstraint, Bank> {

    private static final String messageConstraint = "O banco informado já está sendo utilizado por outro cadastro. Por favor, informe outro.";

    @Override
    public boolean isValid(Bank bank, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        Optional<Bank> categoryOptional = bankRepository.findByName(bank.getName());
        if ((categoryOptional.isPresent()) && (!Objects.equals(categoryOptional.get().getId(), bank.getId()))) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageConstraint)
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
