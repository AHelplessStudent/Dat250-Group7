package com.example.restservice.exception;

public class PollNotFound extends RuntimeException {

    public PollNotFound(Long pid) {
        super("Poll with id " + pid + " not found.");
    }
}
