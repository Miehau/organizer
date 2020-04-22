package org.mmlak.organizer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "tasks")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @Column
    private final String name;
    @Column
    private final String description;
    @Column
    private final boolean done;
    @Column
    private final Instant dateCompleted;
    @ManyToMany(mappedBy = "items", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private final List<ItemList> itemList;
    @Embedded
    private final CoreData coreData;

}



