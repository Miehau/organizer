package org.mmlak.organizer.rest.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mmlak.organizer.repository.entity.ItemList;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemListResponseAttributes extends ResponseAttributes {

    private final ItemListDto itemList;

}
