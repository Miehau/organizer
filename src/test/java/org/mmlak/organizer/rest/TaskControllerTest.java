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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @Mock
    private TaskServiceImpl taskService;

    private TaskController taskController;

    @Before
    public void setUp(){
        this.taskController = new TaskController(taskService);
    }

    @Test
    public void shouldReturnAllTasks(){
        final Task task = new Task();
//        when(taskService.getAll()).thenReturn(singletonList(task));

        ResponseEntity<ResponseDocument> response = taskController.getAll();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getData()).hasSize(1);
        assertThat(response.getBody().getData().get(0).getAttributes()).isExactlyInstanceOf(TasksResponseAttributes.class);
        assertThat(((TasksResponseAttributes)response.getBody().getData().get(0).getAttributes()).getTask()).isEqualTo(task);
    }

    @Test
    public void shouldSaveNewTask(){
        final Task task = new Task();

//        when(taskService.add(task)).thenReturn(task);

        taskController.addTask(task);

        verify(taskService).add(task);
    }
}