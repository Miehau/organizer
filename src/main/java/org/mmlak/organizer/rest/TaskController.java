package org.mmlak.organizer.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ResponseData;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.rest.entity.TaskDTO;
import org.mmlak.organizer.rest.entity.TasksResponseAttributes;
import org.mmlak.organizer.service.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/task", produces = "application/json")
@AllArgsConstructor
@Transactional
@Slf4j
public class TaskController {

    private final TaskServiceImpl taskService;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<ResponseDocument> getAll() {
        return ok(toResponse(taskService.getAll()));
    }

    private ResponseDocument toResponse(final List<TaskDTO> attributes) {
        log.info("Returning tasks: [{}].", attributes);
        return new ResponseDocument(
                attributes.stream()
                        .map(task -> new ResponseData(task.getId(), "task", new TasksResponseAttributes(task)))
                        .collect(toList())
        );
    }

    @GetMapping("/{taskId}")
    @CrossOrigin
    public ResponseEntity<ResponseDocument> get(@PathVariable final String taskId) {
        return ok(toResponse(singletonList(taskService.get(taskId))));
    }

    @PutMapping("/{taskId}")
    @CrossOrigin
    private ResponseEntity<ResponseDocument> updateTask(@RequestBody final Task task) {
        TaskDTO updatedTask = taskService.update(task);
        return ok(toResponse(singletonList(updatedTask)));
    }

    @PostMapping(path = {"/", ""})
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ResponseDocument> addTask(@RequestBody final Task task) {
        final TaskDTO createdTask = taskService.add(task);
        log.debug("Created task [{}].", task);
        return created(URI.create(format("http://localhost:8080/tasks/%s", createdTask.getId())))
                .body(toResponse(singletonList(createdTask)));
    }

    @DeleteMapping("/{taskId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ResponseDocument> removeTask(@PathVariable final UUID taskId) {
        taskService.delete(taskId);
        return noContent().build();
    }
}
