package org.mmlak.organizer.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ItemListResponseAttributes extends ResponseAttributes {

    private final ItemListDto itemList;

}
