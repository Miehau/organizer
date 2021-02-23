package org.mmlak.organizer.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.service.exception.TaskNotFoundException;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TasksRepository repository;

    private TaskServiceImpl taskService;

    @BeforeEach
    public void setUp() {
        this.taskService = new TaskServiceImpl(repository);
    }

    @Test
    public void shouldReturnAllItems() {
        final Task task = new Task();
        final List<Task> tasks = singletonList(task);
        when(repository.findAll()).thenReturn(tasks);

        final List<Task> result = taskService.getAll();

        assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldReturnEmptyArrayIfNoTasks() {
        when(repository.findAll()).thenReturn(emptyList());

        final List<Task> all = taskService.getAll();

        assertThat(all).isEmpty();
    }

    @Test
    public void shouldUpdateTask() {
        final UUID taskId = UUID.randomUUID();
        final Task task = new Task(taskId, "", "", false, null, null);

        when(repository.existsById(taskId)).thenReturn(true);
        when(repository.save(any())).thenReturn(task);

        taskService.update(task);

        verify(repository).save(task);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonExistentTask() {
        final UUID taskId = UUID.randomUUID();
        final Task task = new Task(taskId, "", "", false, null, null);

        when(repository.existsById(taskId)).thenReturn(false);

        Assertions.assertThrows(TaskNotFoundException.class, () -> taskService.update(task));
    }

    @Test
    public void shouldSaveTask() {
        final Task task = new Task(UUID.randomUUID(), "", "", false, null, null);
        when(repository.save(any())).thenReturn(task);
        taskService.add(task);

        verify(repository).save(task);
    }

    @Test
    public void shouldFindTask(){
        final var id = UUID.randomUUID();
        final Task task = new Task(id, "", "", false, null, null);
        when(repository.findById(id)).thenReturn(Optional.of(task));

        final var result = taskService.find(id.toString());

        assertThat(result).isEqualTo(task);
    }

    @Test
    public void shouldDeleteTask(){
        final var taskId = UUID.randomUUID();
        taskService.delete(taskId);

        verify(repository).deleteById(taskId);
    }
}