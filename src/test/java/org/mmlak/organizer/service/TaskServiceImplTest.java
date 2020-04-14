package org.mmlak.organizer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TasksRepository repository;

    private TaskServiceImpl taskService;

    @Before
    public void setUp() {
        this.taskService = new TaskServiceImpl(repository);
    }

    @Test
    public void shouldReturnAllItems() {
        final Task task = new Task();
        final List<Task> tasks = singletonList(task);
        when(repository.findAll()).thenReturn(tasks);

        final List<Task> result = taskService.getAll();

        assertThat(result).isEqualTo(tasks);
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

        taskService.update(task);

        verify(repository).save(task);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenUpdatingNonExistentTask() {
        final UUID taskId = UUID.randomUUID();
        final Task task = new Task(taskId, "", "", false, null, null);

        when(repository.existsById(taskId)).thenReturn(false);

        taskService.update(task);
        //fail
    }

    @Test
    public void shouldSaveTask() {
        final Task task = new Task();

        taskService.add(task);

        verify(repository).save(task);
    }
}