package org.lab.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entity) {
        super(entity + " was not found");
    }

    public EntityNotFoundException(Long entity) {
        super(entity + " was not found");
    }

}
