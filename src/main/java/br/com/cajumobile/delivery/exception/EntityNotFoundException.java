package br.com.cajumobile.delivery.exception;

import java.util.Arrays;

public class EntityNotFoundException extends Exception implements CustomExceptionInterface {

    private final Integer[] id;
    private Class clazz;

    public EntityNotFoundException(Class clazz, Integer... id) {
        this.clazz = clazz;
        this.id = id;
    }

    @Override
    public CauseDTO getCauseDTO() {
        return new CauseDTO("Entity " + clazz.getSimpleName() + " not found with id " + Arrays.toString(id));
    }
}
