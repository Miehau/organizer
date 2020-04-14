package org.mmlak.organizer.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.rest.entity.TasksResponseAttributes;
import org.mmlak.organizer.service.TaskServiceImpl;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TasksControllerTest {

    @Mock
    private TaskServiceImpl taskService;

    private TasksController tasksController;

    @Before
    public void setUp(){
        this.tasksController = new TasksController(taskService);
    }

    @Test
    public void shouldReturnAllTasks(){
        final Task task = new Task();
        when(taskService.getAll()).thenReturn(singletonList(task));

        ResponseEntity<ResponseDocument> response = tasksController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).hasSize(1);
        assertThat(response.getBody().getData().get(0).getAttributes()).isExactlyInstanceOf(TasksResponseAttributes.class);
        assertThat(((TasksResponseAttributes)response.getBody().getData().get(0).getAttributes()).getTask()).isEqualTo(task);
    }

    @Test
    public void shouldSaveNewTask(){
        final Task task = new Task();

        when(taskService.add(task)).thenReturn(task);

        tasksController.addTask(task);

        verify(taskService).add(task);
    }
}