package no.group7;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @EmbeddedId
    private VoteId voteId;

    @MapsId("voterId")
    @ManyToOne
    @JoinColumn(name = "voterId")
    private VoterAcc voterAcc;

    @MapsId("pollId")
    @ManyToOne
    @JoinColumn(name = "pollId")
    private Poll poll;

    // true=yes, false=no
    private boolean type;

    @Override
    public String toString() {
        return "Vote [type=" + type + "]";
    }
}
