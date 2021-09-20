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
    private int num_yes, num_no;

    public Vote() {
        num_yes = 0;
        num_no = 0;
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

    public int getNum_yes() {
        return num_yes;
    }

    public void setNum_yes(int num_yes) {
        this.num_yes = num_yes;
    }

    public int getNum_no() {
        return num_no;
    }

    public void setNum_no(int num_no) {
        this.num_no = num_no;
    }

    @Override
    public String toString() {
        return "Vote [voterEntity=" + from.getEntity_id() + ", hasAccount=" + from.isRegistered() + ", numYes=" + num_yes + ", numNo=" + num_no + "]";
    }
}
