package org.mmlak.organizer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.ItemListRepository;
import org.mmlak.organizer.repository.TasksRepository;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.entity.ItemListDto;
import org.mmlak.organizer.service.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.lang.String.format;
import static org.mmlak.organizer.service.mapper.ItemListMapper.toDto;
import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ListServiceImpl implements ListService {
    private final ItemListRepository itemListRepository;
    private final TasksRepository tasksRepository;

    @Override
    public List<ItemListDto> getAll() {
        return toDto(toList(itemListRepository.findAll()));
    }

    @Override
    public ItemListDto save(ItemList itemList) {
        log.debug("Saving list: [{}].", itemList);
        return toDto(itemListRepository.save(itemList));
    }

    @Override
    public ItemListDto getById(UUID id) {
        return toDto(itemListRepository.findById(id).orElseThrow(() -> new RuntimeException(format("Cannot find any list with id [%s].", id))));
    }

    @Override
    public List<ItemListDto> getListsByTask(Task task) {
        return null;
    }

    @Override
    public void addTaskToList(UUID listId, UUID taskId) {
        Optional<ItemList> list = itemListRepository.findById(listId);
        Optional<Task> task = tasksRepository.findById(taskId);
        if (list.isEmpty() || task.isEmpty()) {
            throw new NotFoundException(list.isEmpty() ? listId.toString() : taskId.toString(), list.isEmpty() ? ItemList.class : Task.class);
        }
        final ItemList itemList = list.get();
        itemList.addTask(task.get());
    }
}
