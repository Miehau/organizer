package org.mmlak.organizer.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.rest.entity.ItemListDto;
import org.mmlak.organizer.rest.entity.ItemListResponseAttributes;
import org.mmlak.organizer.rest.entity.ResponseData;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.service.ListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.mmlak.organizer.service.mapper.ItemListMapper.toDto;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Transactional
@RequestMapping(value = "/list", produces = "application/json")
@Slf4j
@AllArgsConstructor
public class ListController {

    private final ListService listService;

    @GetMapping("/all")
    @CrossOrigin
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDocument> getAllLists() {
        final List<ItemListDto> lists = listService.getAll();
        return ok(toResponse(lists));
    }

    @GetMapping("/{listId}")
    public ResponseEntity<ResponseDocument> getList(@PathVariable UUID listId) {
        final ItemListDto list = toDto(listService.getById(listId));
        return ok(toResponse(singletonList(list)));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<ResponseDocument> add(@RequestBody final ItemList itemList) {
        final ItemListDto createdList = toDto(listService.save(itemList));
        return created(URI.create(format("http://localhost:8080/list/{%s}", createdList.getId())))
                .body(toResponse(singletonList(createdList)));
    }

    @PutMapping("/{listId}/{taskId}")
    public ResponseEntity<ResponseDocument> addTaskToList(@PathVariable UUID listId, @PathVariable UUID taskId) {
        listService.addTaskToList(listId, taskId);
        return ok().build();
    }

    private ResponseDocument toResponse(final List<ItemListDto> attributes) {
        log.info("Returning lists: [{}].", attributes);
        return new ResponseDocument(
                attributes.stream()
                        .map(list -> new ResponseData(list.getId(), "list", new ItemListResponseAttributes(list)))
                        .collect(toList())
        );
    }
}
