package org.mmlak.organizer.service.exception;

import static java.lang.String.format;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String taskId) {
        super(format("Cannot find task with id [%s].", taskId));
    }
}
