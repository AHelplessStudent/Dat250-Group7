package no.group7;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @EmbeddedId
    private VoteId voteId;

    @ManyToOne
    @MapsId("voterId")
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne
    @MapsId("pollId")
    @JoinColumn(name = "poll_id")
    private Poll poll;

    // true=yes, false=no
    private boolean type;

    @Override
    public String toString() {
        return "Vote [type=" + type + ", voter=" + voter.getFirstName() + "]";
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
