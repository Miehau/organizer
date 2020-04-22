package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;

import java.util.List;
import java.util.UUID;

public interface ListService {

    List<ItemList> getAll();

    ItemList save(ItemList itemList);

    ItemList getById(UUID id);

    List<ItemList> getListsByTask(Task task);
}
