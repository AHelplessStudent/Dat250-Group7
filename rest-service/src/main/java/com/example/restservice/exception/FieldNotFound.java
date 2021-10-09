package com.example.restservice.exception;

public class FieldNotFound extends RuntimeException {

    public FieldNotFound(String field) {
        super("Field \"" + field + "\" not found.");
    }
}
