package org.mmlak.organizer.rest;

import org.junit.jupiter.api.Test;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.dto.TaskDTO;
import org.mmlak.organizer.service.TaskService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.main.allow-bean-definition-overriding=true"
)
@Import(TaskControllerIntegrationTestConfig.class)
public class TaskControllerIntegrationTest {

    @Test
    public void shouldGetAllTasks(@LocalServerPort int port) {
        final var response = new TestRestTemplate()
                .exchange(RequestEntity.get(URI.create("http://localhost:" + port + "/task/all"))
                                .accept(MediaType.APPLICATION_JSON)
                                .build()
                        , String.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
    }

    @Test
    public void shouldAddTask(@LocalServerPort int port) {
        final var response = new TestRestTemplate()
                .exchange(RequestEntity.post(URI.create("http://localhost:" + port + "/task/"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .body(new TaskDTO())
                        , String.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        then(response.getBody()).isNotNull();
    }

    @Test
    public void shouldUpdateTask(@LocalServerPort int port) {
        final var task = new TaskDTO(UUID.randomUUID(), "name", "description", false, null, null, null, null);
        final var response = new TestRestTemplate()
                .exchange(RequestEntity.put(URI.create("http://localhost:" + port + "/task"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .body(task)
                        , String.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
    }

    @Test
    public void shouldGetTask(@LocalServerPort int port) {
        final var response = new TestRestTemplate()
                .exchange(RequestEntity.get(URI.create("http://localhost:" + port + "/task/" + UUID.randomUUID().toString()))
                                .accept(MediaType.APPLICATION_JSON)
                                .build()
                        , String.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(response.getBody()).isNotNull();
    }

    @Test
    public void shouldDeleteTask(@LocalServerPort int port) {
        final var response = new TestRestTemplate()
                .exchange(RequestEntity.delete(URI.create("http://localhost:" + port + "/task/" + UUID.randomUUID().toString()))
                                .accept(MediaType.APPLICATION_JSON)
                                .build()
                        , String.class);

        then(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        then(response.getBody()).isNull();
    }
}

@TestConfiguration(proxyBeanMethods = false)
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
class TaskControllerIntegrationTestConfig {


    @Bean
    TaskController taskController(TaskService taskService) {
        return new TaskController(taskService);
    }

    @Bean
    TaskService taskService() {
        return new TaskService() {
            @Override
            public List<Task> getAll() {
                return Arrays.asList(new Task(), new Task());
            }

            @Override
            public Task find(final String taskId) {
                return new Task();
            }

            @Override
            public Task update(final Task task) {
                return task;
            }

            @Override
            public Task add(final Task task) {
                return task;
            }

            @Override
            public void delete(final UUID taskId) {

            }
        };
    }
}