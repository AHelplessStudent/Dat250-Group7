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
    private VoterAcc voterAcc;

    @ManyToOne
    @MapsId("pollId")
    @JoinColumn(name = "poll_id")
    private Poll poll;

    // true=yes, false=no
    private boolean type;

    @Override
    public String toString() {
        return "Vote [type=" + type + ", voter=" + voterAcc.getFirstName() + "]";
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public VoterAcc getVoterAcc() {
        return voterAcc;
    }

    public void setVoterAcc(VoterAcc voterAcc) {
        this.voterAcc = voterAcc;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
