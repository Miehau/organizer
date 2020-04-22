package org.mmlak.organizer.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.ItemListRepository;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.util.CollectionUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static org.mmlak.organizer.util.CollectionUtil.toList;

@Service
@Slf4j
@AllArgsConstructor
public class ListServiceImpl implements ListService {
    private final ItemListRepository itemListRepository;

    @Override
    public List<ItemList> getAll() {
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
}
