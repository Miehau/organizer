package org.mmlak.organizer.rest;

import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler({Exception.class})
    public ResponseEntity handleAnyException(Exception e) {
        log.error("Exception caught", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity handleTaskNotFound(TaskNotFoundException e) {
        log.error("Exception caught", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
