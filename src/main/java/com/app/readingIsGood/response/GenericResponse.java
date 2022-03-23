package com.app.readingIsGood.response;

import lombok.Data;

@Data
public class GenericResponse <T> {

    private T data;
    private String error;

    public boolean hasError() {
        return error != null;
    }

}
