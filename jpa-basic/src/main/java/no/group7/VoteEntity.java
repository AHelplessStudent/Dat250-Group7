package no.group7;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voteEntity")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entity_id;

    // e.g. "machine", "human"
    private String entityType;
    private boolean registered;

    @OneToOne
    private Account account;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes;

    public List<Vote> getVotes() {
        return votes;
    }

    public Long getEntity_id() {
        return entity_id;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    @Override
    public String toString() {
        return "Vote [id=" + entity_id + ", registered=" + registered + ", entityType=" + entityType + "]";
    }
}
