package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<Task> getAll();

    Task update(Task task);

    Task add(Task task);

    void delete(UUID taskId);
}
