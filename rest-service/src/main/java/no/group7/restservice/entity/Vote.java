package no.group7.restservice.entity;

import javax.persistence.*;

@Entity
public class Vote {

    @EmbeddedId
    private VoteCompositeKey id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @MapsId("accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    @MapsId("voteId")
    private Vote vote;

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

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }

    public boolean isVotedYes() {
        return votedYes;
    }

    public void setVotedYes(boolean votedYes) {
        this.votedYes = votedYes;
    }
}