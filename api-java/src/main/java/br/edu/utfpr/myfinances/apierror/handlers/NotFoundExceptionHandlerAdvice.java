package br.edu.utfpr.myfinances.apierror.handlers;

import br.edu.utfpr.myfinances.apierror.model.ApiError;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class NotFoundExceptionHandlerAdvice {

    @ExceptionHandler({ChangeSetPersister.NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ApiError handlerGenericExceptionError(
            Exception exception,
            HttpServletRequest request) {

        return new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getServletPath());
    }

}
