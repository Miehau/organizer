package org.mmlak.organizer.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mmlak.organizer.OrganizerApplication;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = OrganizerApplication.class,
loader = SpringBootContextLoader.class)
public class TaskSteps {

    private List<Task> response;

    @Autowired
    private TaskService taskService;

    @When("^request for all tasks is made$")
    public void getAllTasks(){
        this.response = taskService.getAll();
    }

    @Then("^task list is empty$")
    public void tasksAreEmpty(){
        assertThat(response).isEmpty();
    }

    @Then("^task list contains (\\d) elements$")
    public void tasksListContains(final int size){
        assertThat(response).hasSize(size);
    }
}
