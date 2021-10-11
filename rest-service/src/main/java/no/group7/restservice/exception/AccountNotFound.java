package no.group7.restservice.exception;

public class AccountNotFound extends RuntimeException {

    public AccountNotFound(Long id) {
        super("Account \"" + id + "\" not found.");
    }
}
