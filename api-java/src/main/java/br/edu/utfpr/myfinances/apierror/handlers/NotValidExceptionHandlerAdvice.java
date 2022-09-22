package br.edu.utfpr.myfinances.apierror.handlers;

import br.edu.utfpr.myfinances.apierror.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class NotValidExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiError handlerValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        BindingResult result = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldError: result.getFieldErrors()) {
            validationErrors.put(
                    fieldError.getField(),
                    fieldError.getDefaultMessage());
        }

        return new ApiError(HttpStatus.BAD_REQUEST.value(), "validation error", request.getServletPath(), validationErrors);
    }

}
