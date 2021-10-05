package com.example.restservice;

import java.util.ArrayList;

public class Polls {

    ArrayList<Poll> polls;

    public Polls() {
        this.polls = new ArrayList<>();
    }

    public Polls(ArrayList<Poll> polls) {
        this.polls = polls;
    }

    public ArrayList<Poll> getAll() {
        return polls;
    }

    public void setPolls(ArrayList<Poll> polls) {
        this.polls = polls;
    }

    public void deletePoll(Long id) {

        Poll curr = getPollById(id);
        polls.remove(curr);
    }



    public void add(Poll poll) {
        polls.add(poll);
    }

    public Poll getPollById(Long id) {
        for (Poll t : polls) {
            if (t.getPollId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public void update(Poll poll) {

        Poll curr = getPollById(poll.getPollId());
        int curr_index = polls.indexOf(curr);
        this.polls.set(curr_index, poll);

    }
}
