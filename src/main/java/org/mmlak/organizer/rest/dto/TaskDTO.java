package org.mmlak.organizer.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class TaskDTO extends ResponseAttributes {
    private final UUID id;
    private final String name;
    private final String description;
    private final boolean done;
    private final Instant dateCompleted;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private final List<ItemListDto> itemList;
    private final Instant dateCreated;
    private final Instant dateModified;
}
