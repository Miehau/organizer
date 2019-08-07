package org.mmlak.organizer.rest.entity;

import lombok.Data;
import org.mmlak.organizer.repository.entity.Task;

@Data
public class TasksResponseAttributes extends ResponseAttributes{
    private final Task task;
}
