package org.mmlak.organizer.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDocument {

    private final List<ResponseData> data;

}
