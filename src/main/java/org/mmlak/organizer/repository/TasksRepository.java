package org.mmlak.organizer.repository;

import org.mmlak.organizer.repository.entity.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TasksRepository extends CrudRepository<Task, UUID> {
}
