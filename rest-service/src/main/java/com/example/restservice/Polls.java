package com.example.restservice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class Polls {

    ArrayList<Poll> polls;
    private final AtomicLong head = new AtomicLong(0);

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

    public void removeAll() {
        this.polls.clear();
    }

    public void add(Poll poll) {
        if (poll.getPollId() == null) { // should auto-increment
            poll.setPollId(head.getAndIncrement());
        }

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
