package org.mmlak.organizer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.CoreData;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ItemListDto;
import org.mmlak.organizer.rest.entity.TaskDTO;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.mmlak.organizer.service.mapper.TaskDtoMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.mmlak.organizer.service.mapper.TaskDtoMapper.*;
import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TasksRepository repository;


    @Override
    public List<TaskDTO> getAll() {
        return toList(repository.findAll()).stream()
                .map(TaskDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO get(String taskId) {
        return toDto(repository.findById(UUID.fromString(taskId)).orElseThrow(() -> new TaskNotFoundException(taskId)));
    }

    @Override
    public TaskDTO update(final Task task) {
        if (!repository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId().toString());
        }
        return toDto(repository.save(task));
    }

    @Override
    public TaskDTO add(final Task task) {
        log.debug("Creating task [{}].", task);
        task.setCoreData(new CoreData());
        return toDto(repository.save(task));
    }

    @Override
    public void delete(UUID taskId) {
        repository.deleteById(taskId);
    }

}
