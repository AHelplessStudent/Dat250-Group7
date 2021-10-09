package no.group7.restservice.exception;

public class PollNotFound extends RuntimeException {

    public PollNotFound(Long pid) {
        super("Poll with id " + pid + " not found.");
    }
}
