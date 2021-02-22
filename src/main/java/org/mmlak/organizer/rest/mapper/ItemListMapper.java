package org.mmlak.organizer.rest.mapper;

import org.mmlak.organizer.repository.entity.ItemList;
import org.mmlak.organizer.rest.dto.ItemListDto;

import java.util.List;
import java.util.stream.Collectors;

public class ItemListMapper {

    public static List<ItemListDto> toDto(List<ItemList> list) {
        return list.stream().map(ItemListMapper::toDto).collect(Collectors.toList());
    }

    public static List<ItemListDto> toDtoShallow(List<ItemList> list) {
        return list.stream().map(ItemListMapper::toDtoShallow).collect(Collectors.toList());
    }

    public static ItemListDto toDto(ItemList item) {
        return toDtoCommon(item)
                .items(item.getItems().stream().map(TaskDtoMapper::toDtoShallow).collect(Collectors.toList()))
                .build();
    }

    private static ItemListDto toDtoShallow(ItemList item) {
        return toDtoCommon(item)
                .items(null)
                .build();
    }

    private static ItemListDto.ItemListDtoBuilder toDtoCommon(ItemList item) {
        return ItemListDto.builder()
                .id(item.getId())
                .description(item.getDescription())
                .visibleOnDashboard(item.isVisibleOnDashboard());
    }
}
