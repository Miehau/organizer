package org.mmlak.organizer.rest.mapper;

import org.mmlak.organizer.repository.entity.Task;
import org.mmlak.organizer.rest.dto.TaskDTO;

public final class TaskDtoMapper {
    private TaskDtoMapper() {
    }

    public static TaskDTO toDto(Task task) {
        return toDtoCommon(task)
                .itemList(ItemListMapper.toDtoShallow(task.getItemList()))
                .build();
    }

    public static TaskDTO toDtoShallow(Task task) {
        return toDtoCommon(task)
                .itemList(null)
                .build();
    }

    private static TaskDTO.TaskDTOBuilder toDtoCommon(Task task) {
        return TaskDTO.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .dateCompleted(task.getDateCompleted());
//                .dateCreated(task.getCoreData().getDateCreated())
//                .dateModified(task.getCoreData().getDateModified());
    }

}
