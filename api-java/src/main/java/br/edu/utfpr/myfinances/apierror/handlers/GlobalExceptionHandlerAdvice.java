package br.edu.utfpr.myfinances.apierror.handlers;

import br.edu.utfpr.myfinances.apierror.exception.DataNotFoundException;
import br.edu.utfpr.myfinances.apierror.exception.LinkedRegisterException;
import br.edu.utfpr.myfinances.apierror.model.ApiError;
import org.springframework.data.crossstore.ChangeSetPersister;
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
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiError handlerArgumentNotValidException(
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

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiError handlerNotFoundExceptionError(
            Exception exception,
            HttpServletRequest request) {

        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiError handlerDataNotFoundException(
            Exception exception,
            HttpServletRequest request) {

        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler({LinkedRegisterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private ApiError handlerLinkedRegisterException(
            LinkedRegisterException exception,
            HttpServletRequest request) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), request.getServletPath());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ApiError handlerExceptionError(
            Exception exception,
            HttpServletRequest request) {
        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getServletPath());
    }

}
