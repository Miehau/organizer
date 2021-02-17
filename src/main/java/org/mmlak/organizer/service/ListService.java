package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ItemListDto;

import java.util.List;
import java.util.UUID;

public interface ListService {

    List<ItemListDto> getAll();

    ItemListDto save(ItemList itemList);

    ItemListDto getById(UUID id);

    List<ItemListDto> getListsByTask(Task task);

    void addTaskToList(UUID listId, UUID taskId);
}
