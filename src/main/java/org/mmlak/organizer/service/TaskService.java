package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.TaskDTO;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getAll();

    TaskDTO get(String taskId);

    TaskDTO update(Task task);

    TaskDTO add(Task task);

    Task findTaskById(String taskId);

    void delete(UUID taskId);
}
