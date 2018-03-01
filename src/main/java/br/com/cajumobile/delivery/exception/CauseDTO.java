package br.com.cajumobile.delivery.exception;

import lombok.Getter;

public class CauseDTO {

    @Getter
    private String cause;

    public CauseDTO(String cause) {
        this.cause = cause;
    }
}
