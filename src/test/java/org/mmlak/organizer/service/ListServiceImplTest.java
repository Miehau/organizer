package org.mmlak.organizer.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mmlak.organizer.repository.ItemListRepository;
import org.mmlak.organizer.repository.entity.CoreData;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.repository.entity.Task;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListServiceImplTest {

    @Mock
    private ItemListRepository itemListRepository;

    @Mock
    private TaskService taskService;

    private ListServiceImpl listService;

    @Before
    public void setUp() {
        listService = new ListServiceImpl(itemListRepository, taskService);
    }

    @Test
    public void shouldFindAll() {
        final var listId = UUID.randomUUID();
        final var itemList = createItemList(listId);
        when(itemListRepository.findAll()).thenReturn(singletonList(itemList));

        final var result = listService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(itemList);
    }

    private ItemList createItemList(final UUID id) {
        return new ItemList(
                id,
                "listName",
                "listDescription",
                true,
                new ArrayList<>(),
                new CoreData()
        );
    }

    @Test
    public void shouldSave() {
        final var itemList = createItemList(UUID.randomUUID());
        when(itemListRepository.save(any())).thenReturn(itemList);

        final var result = listService.save(itemList);

        assertThat(result).isEqualTo(itemList);
    }

    @Test
    public void shouldGetById() {
        final var id = UUID.randomUUID();
        final var itemList = this.createItemList(id);
        when(itemListRepository.findById(id)).thenReturn(Optional.of(itemList));

        final var result = listService.getById(id);

        assertThat(result).isEqualTo(itemList);
    }

    @Test
    public void shouldFindListsByTask() {
        //todo
    }

    @Test
    public void shouldAddTaskToList() {
        final var taskId = UUID.randomUUID();
        final var listId = UUID.randomUUID();
        final var itemList = this.createItemList(listId);
        when(itemListRepository.findById(listId)).thenReturn(Optional.of(itemList));
        when(taskService.findTaskById(taskId.toString())).thenReturn(new Task());

        listService.addTaskToList(listId, taskId);

        assertThat(itemList.getItems()).hasSize(1);
    }
}