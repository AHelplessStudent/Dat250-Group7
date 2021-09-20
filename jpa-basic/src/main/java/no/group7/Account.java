package no.group7;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Poll> polls;

    @OneToOne
    private VoteEntity voteEntity;

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

    public Collection<Poll> getPolls() {
        return polls;
    }

    public void setPolls(Collection<Poll> polls) {
        this.polls = polls;
    }

    public VoteEntity getVoteEntity() {
        return voteEntity;
    }

    public void setVoteEntity(VoteEntity voteEntity) {
        this.voteEntity = voteEntity;
    }

    @Override
    public String toString() {
        return "Account [Username=" + username + ", FirstName=" + firstName + ", LastName=" + lastName + ", " + password + "]";
    }
}

