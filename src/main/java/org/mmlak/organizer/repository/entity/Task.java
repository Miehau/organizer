package org.mmlak.organizer.repository.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor(force = true)
@Entity(name = "tasks")
public class Task {

    @Id
    private final UUID id;
    @Column
    private final String name;
    @Column
    private final String description;
    @Column
    private final boolean done;
    @Column
    private final Instant dateCompleted;
    private final CoreData coreData;

}
