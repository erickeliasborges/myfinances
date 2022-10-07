package br.edu.utfpr.myfinances.apierror.exception;

public class LinkedRegisterException extends RuntimeException {

    private String message;

    public LinkedRegisterException(String message) {
        super(message);
    }

}
