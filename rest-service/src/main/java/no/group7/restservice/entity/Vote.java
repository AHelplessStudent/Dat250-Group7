package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vote {

    @EmbeddedId
    private VoteCompositeKey id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    @MapsId("pollId")
    @JsonIgnore
    private Poll poll;

    private boolean votedYes;

    public Vote(boolean votedYes) {
        this.votedYes = votedYes;
    }

    public Vote() {
    }

    public VoteCompositeKey getId() {
        return id;
    }

    public void setId(VoteCompositeKey id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public boolean isVotedYes() {
        return votedYes;
    }

    public void setVotedYes(boolean votedYes) {
        this.votedYes = votedYes;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", account=" + account.getId() +
                ", poll=" + poll.getId() +
                ", votedYes=" + votedYes +
                '}';
    }
}