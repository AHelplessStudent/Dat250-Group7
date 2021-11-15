package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    private String authId;

    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    private List<Poll> polls;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    @JsonIgnore
    private List<Vote> votes;

    public Account() {
    }

    public Account(Long id, String username, String firstName, String lastName, String authId) {
        this.id = id;
        this.authId = authId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Poll> getPolls() {
        return polls;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
    }

    @Override
    public String toString() {
        return "Account [Username=" + username + ", FirstName=" + firstName + ", LastName=" + lastName + "]";
    }

    public void setAccountId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(polls, account.polls) && Objects.equals(votes, account.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, polls, votes);
    }
}
