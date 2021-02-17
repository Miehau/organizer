package org.mmlak.organizer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
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
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "items", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private final List<ItemList> itemList = new ArrayList<>();
    @Embedded
    private CoreData coreData;

}



