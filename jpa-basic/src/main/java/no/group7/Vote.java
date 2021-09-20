package no.group7;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private VoteEntity from;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Poll poll;

    // number of yes/no votes
    private int numYes, numNo;

    public Vote() {
        numYes = 0;
        numNo = 0;
    }

    public VoteEntity getFrom() {
        return from;
    }

    public void setFrom(VoteEntity from) {
        this.from = from;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public int getNumYes() {
        return numYes;
    }

    public void setNumYes(int numYes) {
        this.numYes = numYes;
    }

    public int getNumNo() {
        return numNo;
    }

    public void setNumNo(int numNo) {
        this.numNo = numNo;
    }

    @Override
    public String toString() {
        return "Vote [voterEntity=" + from.getEntity_id() + ", hasAccount=" + from.isRegistered() + ", numYes=" + numYes + ", numNo=" + numNo + "]";
    }
}
