package org.mmlak.organizer.rest.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ItemListDto extends ResponseAttributes {
    private final UUID id;
    private final String name;
    private final String description;
    private final boolean visibleOnDashboard;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private List<TaskDTO> items;
}
