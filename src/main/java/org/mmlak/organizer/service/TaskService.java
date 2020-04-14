package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();

    Task update(Task task);

    Task add(Task task);
}
