package br.com.cajumobile.delivery.exception;

public class PreconditionFailed extends Exception implements CustomExceptionInterface {
    private String message;

    public PreconditionFailed(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public CauseDTO getCauseDTO() {
        return new CauseDTO(message);
    }
}
