package org.mmlak.organizer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.ItemListRepository;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.dto.ItemListDto;
import org.mmlak.organizer.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.mmlak.organizer.rest.mapper.ItemListMapper.toDto;
import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ListServiceImpl implements ListService {
    private final ItemListRepository itemListRepository;
    private final TaskService taskService;

    @Override
    @Deprecated
    public List<ItemListDto> getAll() {
        return toDto(toList(itemListRepository.findAll()));
    }

    @Override
    public List<ItemList> findAll() {
        return toList(itemListRepository.findAll());
    }

    @Override
    public ItemList save(ItemList itemList) {
        log.debug("Saving list: [{}].", itemList);
        return itemListRepository.save(itemList);
    }

    @Override
    public ItemList getById(UUID id) {
        return itemListRepository.findById(id).orElseThrow(() -> new RuntimeException(format("Cannot find any list with id [%s].", id)));
    }

    @Override
    public List<ItemList> getListsByTask(Task task) {
        return null;
    }

    @Override
    public void addTaskToList(UUID listId, UUID taskId) {
        Optional<ItemList> list = itemListRepository.findById(listId);
        Task task = taskService.findTaskById(taskId.toString());
        if (list.isEmpty()) {
            throw new NotFoundException(listId.toString(), ItemList.class);
        }
        final ItemList itemList = list.get();
        itemList.addTask(task);
    }
}
