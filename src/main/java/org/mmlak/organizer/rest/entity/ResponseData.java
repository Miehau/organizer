package org.mmlak.organizer.rest.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseData {

    private final UUID id;
    private final String type;
    private final ResponseAttributes attributes;
}
