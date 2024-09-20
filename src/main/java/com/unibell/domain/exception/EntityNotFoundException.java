package com.unibell.domain.exception;

import static java.lang.String.format;

public class EntityNotFoundException extends jakarta.persistence.EntityNotFoundException {
    public EntityNotFoundException(String entityName, Object id) {
        super(format("%s with id '%s' not found", entityName, id));
    }
}
