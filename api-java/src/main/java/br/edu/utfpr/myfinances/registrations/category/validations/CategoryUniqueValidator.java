package br.edu.utfpr.myfinances.registrations.category.validations;

import br.edu.utfpr.myfinances.registrations.category.Category;
import br.edu.utfpr.myfinances.registrations.category.CategoryRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public record CategoryUniqueValidator(
        CategoryRepository categoryRepository) implements ConstraintValidator<CategoryUniqueConstraint, Category> {

    private static final String messageConstraint = "A categoria informada já está sendo utilizada por outro cadastro. Por favor, informe outra.";

    @Override
    public boolean isValid(Category category, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();

        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if ((categoryOptional.isPresent()) && (!Objects.equals(categoryOptional.get().getId(), category.getId()))) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageConstraint)
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
