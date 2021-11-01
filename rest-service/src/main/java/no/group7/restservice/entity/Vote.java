package no.group7.restservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO remove json back reference (handeled by DTO now)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Poll poll;

    // number of yes/no votes
    private int num_yes, num_no;

    public Vote(int num_yes, int num_no) {
        super();
        this.num_yes = num_yes;
        this.num_no = num_no;
    }

    public Vote() {
        num_yes = 0;
        num_no = 0;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public void setVoteId(Long id) {
        this.id = id;
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
        return "Vote [numYes=" + num_yes + ", numNo=" + num_no + "]";
    }

    public Long getVoteId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return num_yes == vote.num_yes && num_no == vote.num_no && id.equals(vote.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, num_yes, num_no);
    }
}