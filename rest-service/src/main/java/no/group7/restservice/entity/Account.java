package no.group7.restservice.entity;

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
    private String password;
    private String firstName;
    private String lastName;

    @OneToMany(orphanRemoval = true)
    private List<Poll> polls;

    @OneToMany(mappedBy = "account", orphanRemoval = true)
    private List<Vote> votes;

    public Account() {
    }

    public Account(Long id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return Objects.equals(id, account.id) && Objects.equals(username, account.username) && Objects.equals(password, account.password) && Objects.equals(firstName, account.firstName) && Objects.equals(lastName, account.lastName) && Objects.equals(polls, account.polls) && Objects.equals(votes, account.votes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, lastName, polls, votes);
    }
}
