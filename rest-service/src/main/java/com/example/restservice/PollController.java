package com.example.restservice;

import org.springframework.web.bind.annotation.*;

@RestController
/**
 * Controller for REST-api starting with '/polls'.
 */
public class PollController {

    private final Polls polls = new Polls();

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping("/polls")
    public Polls getPolls() {
        return polls;
    }

    @GetMapping("/polls/{pid}")
    public Poll getPoll(@PathVariable("pid") Long pid) {
        return polls.getPollById(pid);
    }

    @GetMapping("/polls/{pid}/{field}")
    public Object getPollField(@PathVariable("pid") Long pid, @PathVariable("field") String field) {
        Poll poll = polls.getPollById(pid);
        switch (field) {
            case "title":
                return poll.getTitle();

            case "deadline":
                return poll.getDeadline();

            case "isPublic":
                return poll.isPublic();

            default:
                return null;
        }
    }

    @GetMapping("/polls/{pid}/votes")
    /**
     * View votes on a poll with given poll-id.
     */
    public Votes getPollVotes(@PathVariable("pid") Long pid) {
        return polls.getPollById(pid).getVotes();
    }

    @GetMapping("/polls/{pid}/votes/{vid}")
    /**
     * View vote on a given poll.
     */
    public Vote getPollVote(@PathVariable("pid") Long pid, @PathVariable("pid") Long vid) {
        return polls.getPollById(pid).getVotes().getVoteById(vid);
    }

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping("/polls")
    public Polls postPoll(@RequestBody Poll poll) {
        polls.add(poll);
        return polls;
    }

    @PostMapping("/polls/{pid}/votes")
    public Poll postPollVote(@PathVariable("pid") Long pid, @RequestBody Vote newValue) {

        Poll p = polls.getPollById(pid);

        newValue.setPollId(p.getPollId());
        p.getVotes().add(newValue);

        return p;
    }

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("/polls")
    public Polls deletePolls() {
        polls.removeAll();
        return polls;
    }

    @DeleteMapping("/polls/{pid}")
    public Polls deletePoll(@PathVariable("pid") Long pid) {
        polls.deletePoll(pid);
        return polls;
    }

    @DeleteMapping("/polls/{pid}/votes")
    public Poll deletePollVotes(@PathVariable("pid") Long pid) {
        Poll poll = polls.getPollById(pid);
        poll.getVotes().removeAll();
        return poll;
    }

    @DeleteMapping("/polls/{pid}/votes/{vid}")
    public Vote deletePollVote(@PathVariable("pid") Long pid, @PathVariable("vid") Long vid) {
        Vote vote = polls.getPollById(pid).getVotes().getVoteById(vid);
        polls.getPollById(pid).getVotes().deleteVote(vid);
        return vote;
    }

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("/polls/{pid}")
    public Polls putPoll(@PathVariable("pid") Long pid, @RequestBody Poll poll) {
        Poll currPoll = polls.getPollById(pid);

        // if the given poll id doesn't correspond to an existing poll.
        if (currPoll == null) {
            polls.add(new Poll(pid, poll.getTitle(), poll.getDeadline(), poll.isPublic()));
            return polls;
        }

        currPoll.setTitle(poll.getTitle());
        currPoll.setDeadline(poll.getDeadline());
        currPoll.setPublic(poll.isPublic());

        return polls;
    }

    @PutMapping("/polls/{pid}/votes/{vid}")
    public Vote putPollVote(@PathVariable("pid") Long pid, @PathVariable("vid") Long vid, @RequestBody Vote newValue) {
        Poll poll = polls.getPollById(pid);
        Vote currVote = poll.getVotes().getVoteById(vid);

        if (currVote == null) {
            poll.getVotes().add(newValue);
        }

        return newValue;
    }
}