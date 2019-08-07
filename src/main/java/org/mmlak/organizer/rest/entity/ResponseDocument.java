package org.mmlak.organizer.rest.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDocument {

    private final List<ResponseData> data;

}
