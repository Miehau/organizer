package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
public class TaskService {

    private final TasksRepository repository;

    public TaskService(final TasksRepository repository){
        this.repository = repository;
    }

    public List<Task> getAll(){
        return toList(repository.findAll());
    }
}
