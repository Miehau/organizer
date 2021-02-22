package org.mmlak.organizer.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TasksResponseAttributes extends ResponseAttributes{
    private final TaskDTO task;
}
