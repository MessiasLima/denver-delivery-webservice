package br.com.cajumobile.delivery.exception;

import lombok.Getter;

public class FileNotFoundException extends Exception implements CustomExceptionInterface {
    @Getter
    private String fileName;

    public FileNotFoundException(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CauseDTO getCauseDTO() {
        return new CauseDTO(getFileName() + " was not found in server");
    }
}
