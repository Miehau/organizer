package org.mmlak.organizer.rest.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mmlak.organizer.repository.entity.Task;

@Data
@EqualsAndHashCode(callSuper = false)
public class TasksResponseAttributes extends ResponseAttributes{
    private final Task task;
}
