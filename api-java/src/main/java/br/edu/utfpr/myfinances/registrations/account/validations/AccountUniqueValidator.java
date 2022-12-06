package br.edu.utfpr.myfinances.registrations.account.validations;

import br.edu.utfpr.myfinances.registrations.account.Account;
import br.edu.utfpr.myfinances.registrations.account.AccountRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public record AccountUniqueValidator(
        AccountRepository accountRepository) implements ConstraintValidator<AccountUniqueConstraint, Account> {

    private static final String messageConstraint = "Já existe uma conta com esses dados. Por favor, informe outra.";

    @Override
    public boolean isValid(Account account, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        Optional<Account> accountOptional = accountRepository.findByAgencyAndNumberAndBank(account.getAgency(), account.getNumber(), account.getBank());
        if ((accountOptional.isPresent()) && (!Objects.equals(accountOptional.get().getId(), account.getId()))) {
            addConstraint(constraintValidatorContext, "agency");
            addConstraint(constraintValidatorContext, "number");
            addConstraint(constraintValidatorContext, "bank");
            return false;
        }

        return true;
    }

    private void addConstraint(ConstraintValidatorContext constraintValidatorContext, String fieldName) {
        constraintValidatorContext.buildConstraintViolationWithTemplate(messageConstraint)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }

}
