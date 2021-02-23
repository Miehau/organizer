package org.mmlak.organizer.rest;

import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.service.exception.NotFoundException;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        log.error("Exception caught", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity handleException(TaskNotFoundException e) {
        log.error("Exception caught", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleException(NotFoundException e) {
        log.error("Exception caught", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
