package br.com.cajumobile.delivery.exception;

import lombok.Getter;

public class InvalidFileException extends Exception implements CustomExceptionInterface {

    @Getter
    private String message;

    public InvalidFileException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public CauseDTO getCauseDTO() {
        return new CauseDTO(getMessage());
    }
}
