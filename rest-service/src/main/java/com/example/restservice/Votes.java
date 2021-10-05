package com.example.restservice;

import java.util.ArrayList;

public class Votes {

    ArrayList<Vote> votes;

    public Votes() {
        this.votes = new ArrayList<>();
    }

    public Votes(ArrayList<Vote> polls) {
        this.votes = polls;
    }

    public ArrayList<Vote> getAll() {
        return votes;
    }

    public void setVotes(ArrayList<Vote> votes) {
        this.votes = votes;
    }

    public void deleteVotes(Long id) {
        Vote curr = getVoteById(id);
        votes.remove(curr);
    }



    public void add(Vote vote) {
        votes.add(vote);
    }

    public Vote getVoteById(Long id) {
        for (Vote t : votes) {
            if (t.getVoteId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public void update(Vote vote) {

        Vote curr = getVoteById(vote.getVoteId());
        int curr_index = votes.indexOf(curr);
        this.votes.set(curr_index, vote);

    }
}
