package org.mmlak.organizer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.Task;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private TasksRepository repository;

    private TaskService taskService;

    @Before
    public void setUp(){
        this.taskService = new TaskService(repository);
    }

    @Test
    public void shouldReturnAllItems(){
        final var task = new Task();
        final var tasks = singletonList(task);
        when(repository.findAll()).thenReturn(tasks);

        final var result = taskService.getAll();

        assertThat(result).isEqualTo(tasks);
    }

    @Test
    public void shouldReturnEmptyArrayIfNoTasks(){
        when(repository.findAll()).thenReturn(emptyList());

        final var all = taskService.getAll();

        assertThat(all).isEmpty();
    }

}