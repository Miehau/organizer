package org.mmlak.organizer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.Instant;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Embeddable
public class CoreData {

    @Column(columnDefinition = "TIMESTAMP default current_timestamp")
    private final Instant dateCreated;
    @Column(columnDefinition = "TIMESTAMP default current_timestamp ON UPDATE current_timestamp")
    private final Instant dateModified;
}
