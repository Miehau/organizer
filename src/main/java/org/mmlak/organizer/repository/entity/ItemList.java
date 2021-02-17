package org.mmlak.organizer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "item_list")
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ItemList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final UUID id;
    @Column
    private final String name;
    @Column
    private final String description;
    @Column
    private final boolean visibleOnDashboard;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "itemlist_item",
            joinColumns = {@JoinColumn(name = "list_id")},
            inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private List<Task> items = new ArrayList<>();
    @Embedded
    private final CoreData coreData;

    public void addTask(Task task) {
        this.items.add(task);
    }
}
