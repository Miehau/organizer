package org.mmlak.organizer.service;

import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TasksRepository repository;

    public TaskServiceImpl(final TasksRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Task> getAll() {
        return toList(repository.findAll());
    }

    @Override
    public Task update(final Task task) {
        if (!repository.existsById(task.getId())) {
            throw new RuntimeException(format("Cannot update task [%s], as it does not exist!", task.getId()));
        }
        return repository.save(task);
    }

    @Override
    public Task add(final Task task) {
        log.debug("Creating task [{}].", task);
        return repository.save(task);
    }

    @Override
    public void delete(UUID taskId) {
        repository.deleteById(taskId);
    }
}
