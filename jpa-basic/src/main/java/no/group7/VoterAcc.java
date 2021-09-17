package no.group7;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voterAcc")
public class VoterAcc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voterId;

    private String username;
    private String password;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "voterId")
    private List<Vote> votes;

    // private Collection<Vote> votes;

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

    @Override
    public String toString() {
        // TODO also return votes variable.
        return "VoterAcc [Username=" + username + ", Password=" + password + ", FirstName=" + firstName + ", LastName=" + lastName + "]";
    }
}

