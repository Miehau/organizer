package org.mmlak.organizer.service;

import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (repository.findById(task.getId()).isEmpty()){
            throw new RuntimeException("Cannot update task, as it does not exist!");
        }
        return repository.save(task);
    }

    @Override
    public Task add(final Task task){
        log.debug("Creating task [{}].", task);
        return repository.save(task);
    }
}