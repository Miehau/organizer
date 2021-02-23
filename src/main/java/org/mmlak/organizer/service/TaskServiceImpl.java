package org.mmlak.organizer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.CoreData;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TasksRepository repository;


    @Override
    public List<Task> getAll() {
        return toList(repository.findAll());
    }

    @Override
    public Task find(String taskId) {
        return repository.findById(UUID.fromString(taskId)).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    public Task update(final Task task) {
        if (!repository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId().toString());
        }
        return repository.save(task);
    }

    @Override
    public Task add(final Task task) {
        log.debug("Creating task [{}].", task);
        task.setCoreData(new CoreData());
        return repository.save(task);
    }

    @Override
    public void delete(UUID taskId) {
        repository.deleteById(taskId);
    }

}
