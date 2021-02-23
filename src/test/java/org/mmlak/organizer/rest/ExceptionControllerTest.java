package org.mmlak.organizer.rest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mmlak.organizer.service.exception.NotFoundException;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
class ExceptionControllerTest {

    private ExceptionController exceptionController = new ExceptionController();

    @Test
    public void shouldHandleGenericException(){
        final var response = exceptionController.handleException(new Exception());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldHandleTaskNotFoundException(){
        final var response = exceptionController.handleException(new TaskNotFoundException("id"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

    @Test
    public void shouldHandleNotFoundException(){
        final var response = exceptionController.handleException(new NotFoundException("id", Object.class));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNull();
    }

}