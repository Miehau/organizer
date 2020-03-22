package org.mmlak.organizer.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ResponseData;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.rest.entity.TasksResponseAttributes;
import org.mmlak.organizer.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/tasks", produces = "application/json")
@AllArgsConstructor
@Slf4j
public class TasksController {

    private final TaskService taskService;

    @GetMapping("/all")
    @CrossOrigin
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
    @CrossOrigin(origins = "http://localhost:3000")
    private ResponseEntity<ResponseDocument> updateTask(@PathVariable final UUID taskId, @RequestBody  final Task task){
        Task updatedTask = taskService.updateTask(task);
        return ok(toResponse(Collections.singletonList(updatedTask)));
    }
}
