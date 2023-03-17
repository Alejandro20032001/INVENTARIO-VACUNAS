package com.kruger.pruebatecnica.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpResponseMessage {
    FIND_SUCCESSFUL("It was found correctly"),
    PERSIST_SUCCESSFUL("It was registered correctly"),
    UPDATE_SUCCESSFUL("It was updated correctly"),
    UPDATE_ERROR("It could not be updated"),
    DELETE_SUCCESSFUL("It was deleted correctly"),
    DELETE_ERROR("It could not be deleted"),

    NOT_FOUND_RECORD("It was not found the record"),
    NOT_MATCH_FILTER("It was not found the record with the filter");
    private final String value;
}
