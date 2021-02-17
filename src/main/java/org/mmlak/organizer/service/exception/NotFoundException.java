package org.mmlak.organizer.service.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String id, Class<?> clazz) {
        super(format("Entity [%s] with id [%s]} not found.", clazz.getSimpleName(), id));
    }
}
