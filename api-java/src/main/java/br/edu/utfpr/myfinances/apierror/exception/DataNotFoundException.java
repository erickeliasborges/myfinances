package br.edu.utfpr.myfinances.apierror.exception;

public class DataNotFoundException extends RuntimeException {

    private String message;

    public DataNotFoundException(String message) {
        super(message);
    }

}
