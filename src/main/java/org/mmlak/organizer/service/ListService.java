package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ItemListDto;

import java.util.List;
import java.util.UUID;

public interface ListService {

    List<ItemListDto> getAll();

    List<ItemList> findAll();

    ItemList save(ItemList itemList);

    ItemList getById(UUID id);

    List<ItemList> getListsByTask(Task task);

    void addTaskToList(UUID listId, UUID taskId);
}
