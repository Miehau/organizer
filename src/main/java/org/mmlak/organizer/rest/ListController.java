package org.mmlak.organizer.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.rest.entity.ItemListResponseAttributes;
import org.mmlak.organizer.rest.entity.ResponseData;
import org.mmlak.organizer.rest.entity.ResponseDocument;
import org.mmlak.organizer.service.ListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/list", produces = "application/json")
@Slf4j
@AllArgsConstructor
public class ListController {

    private final ListService listService;

    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    public ResponseEntity<ResponseDocument> getAllLists(){
        final List<ItemList> lists = listService.getAll();
        return ok(toResponse(lists));
    }

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<ResponseDocument> save(@RequestBody final ItemList itemList){
        final ItemList createdList = listService.save(itemList);
        return created(URI.create(format("http://192.168.0.26:9090/list/{%s}", createdList.getId())))
                .body(toResponse(singletonList(createdList)));
    }

    private ResponseDocument toResponse(final List<ItemList> attributes) {
        log.info("Returning lists: [{}].", attributes);
        return new ResponseDocument(
                attributes.stream()
                        .map(task -> new ResponseData(task.getId(), "tasks", new ItemListResponseAttributes(task)))
                        .collect(toList())
        );
    }
}
