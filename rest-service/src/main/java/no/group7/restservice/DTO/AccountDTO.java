package no.group7.restservice.DTO;

import java.util.Set;

public class AccountDTO {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;

    private Set<Long> pollIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Long> getPollIds() {
        return pollIds;
    }

    public void setPollIds(Set<Long> pollIds) {
        this.pollIds = pollIds;
    }
}
