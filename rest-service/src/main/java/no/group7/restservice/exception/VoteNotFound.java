package no.group7.restservice.exception;

public class VoteNotFound extends RuntimeException {

    public VoteNotFound(Long vid) {
        super("Vote with id " + vid + " not found.");
    }
}
