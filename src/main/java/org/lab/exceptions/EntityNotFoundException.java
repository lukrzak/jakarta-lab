package org.lab.exceptions;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String entity) {
        super(entity + " was not found");
    }
}
