package com.example.restservice;

public class Vote {

    private Long voteId;

    private Long pollId;

    // number of yes/no votes
    private int num_yes, num_no;

    public Vote(Long voteId, int num_yes, int num_no) {
        this.voteId = voteId;
        this.num_yes = num_yes;
        this.num_no = num_no;
    }

    public Vote() {
        num_yes = 0;
        num_no = 0;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
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
        return voteId;
    }
}