package no.group7;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "voter")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voterId;

    private String firstName;
    private String accountType;

    @OneToOne
    private VoterAcc account;

    @OneToMany(mappedBy = "voter")
    private List<Vote> votes;


    public VoterAcc getAccount() {
        return account;
    }

    public void setAccount(VoterAcc voterAcc) {
        this.account = voterAcc;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
