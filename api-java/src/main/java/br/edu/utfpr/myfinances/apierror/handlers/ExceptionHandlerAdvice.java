package br.edu.utfpr.myfinances.apierror.handlers;

import br.edu.utfpr.myfinances.apierror.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ApiError handlerGenericExceptionError(
            Exception exception,
            HttpServletRequest request) {

        return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage(), request.getServletPath());
    }

}
