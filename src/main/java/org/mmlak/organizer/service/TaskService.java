package org.mmlak.organizer.service;

import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
public class TaskService {

    private final TasksRepository repository;

    public TaskService(final TasksRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAll() {
        return toList(repository.findAll());
    }

    public Task updateTask(final Task task) {
        if (!repository.findById(task.getId()).isPresent()){
            throw new RuntimeException("Cannot update task, as it does not exist!");
        }
        return repository.save(task);
    }
}
