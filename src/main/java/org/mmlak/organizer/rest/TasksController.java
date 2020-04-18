package org.mmlak.organizer.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ResponseData;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.rest.entity.TasksResponseAttributes;
import org.mmlak.organizer.service.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/tasks", produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TasksController {

    private final TaskServiceImpl taskService;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseDocument> getAll() {
        return ok(toResponse(taskService.getAll()));
    }

    private ResponseDocument toResponse(final List<Task> attributes) {
        log.info("Returning tasks: [{}].", attributes);
        return new ResponseDocument(
                attributes.stream()
                        .map(task -> new ResponseData(task.getId(), "tasks", new TasksResponseAttributes(task)))
                        .collect(toList())
        );
    }

    @PutMapping("/{taskId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    private ResponseEntity<ResponseDocument> updateTask(@PathVariable final UUID taskId, @RequestBody final Task task) {
        Task updatedTask = taskService.update(task);
        return ok(toResponse(Collections.singletonList(updatedTask)));
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ResponseDocument> addTask(@RequestBody final Task task) {
        final Task createdTask = taskService.add(task);
        log.debug("Created task [{}].", task);
        return created(URI.create(format("http://localhost:8080/tasks/%s", createdTask.getId())))
                .body(toResponse(Collections.singletonList(task)));
    }

    @DeleteMapping("/{taskId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ResponseDocument> removeTask(@PathVariable final UUID taskId){
        taskService.delete(taskId);
        return noContent().build();

    }
}
