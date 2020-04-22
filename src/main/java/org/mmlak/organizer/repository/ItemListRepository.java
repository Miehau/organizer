package org.mmlak.organizer.repository;

import org.mmlak.organizer.repository.entity.ItemList;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemListRepository extends CrudRepository<ItemList, UUID> {
}
