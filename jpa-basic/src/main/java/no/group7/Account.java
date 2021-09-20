package no.group7;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String firstName;
    private String lastName;

    @OneToMany
    private Collection<Poll> polls;

    @OneToOne
    private Voter voter;

    public void setPolls(Collection<Poll> polls) {
        this.polls = polls;
    }

    public Collection<Poll> getPolls() {
        return polls;
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

    private String printPolls() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (Poll poll : polls) {
            result.append("\n     ");
            result.append(poll).append(", ");
        }
        result.append("]");
        return result.toString();
    }


    @Override
    public String toString() {
        return "UserAcc [Username=" + username + ", Password=" + password + ", FirstName=" + firstName + ", LastName=" + lastName + "" + printPolls() + "]";
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }
}

