package com.example.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
/**
 * Controller for REST-api starting with '/polls'.
 */
public class PollController {

    /**
     * TODO: change return types on the methods (not all should be String)
     * This class is unfinished.
     */
    private final AtomicLong counter = new AtomicLong();

    private Polls polls = new Polls();

    @GetMapping("/polls")
    public Polls getPolls() {
        return polls;
        // return "[ GET ] Here is all the polls";
    }

    @DeleteMapping("/polls")
    public Polls deletePolls() {
        polls = new Polls();
        return polls;
//        return "[ DELETE ] Deleting all polls";
    }

    @GetMapping("/polls/{pid}")
    /**
     * View poll with given id.
     */
    public Poll getPoll(@PathVariable("pid") Long pid) {

        return polls.getPollById(pid);
        //return "[ GET ] Poll with id=" + pid;
    }

    @PostMapping("/polls/{pid}")
    public Polls postPoll(@PathVariable("pid") Long pid, @RequestBody Poll poll) {

        // poopy way of doing it. Please come up with something better.
        if (polls.getPollById(pid) != null)
            return null;

        Poll complete_poll = new Poll(pid, poll.getTitle(), poll.getDeadline(), poll.isPublic());
        polls.add(complete_poll);
        return polls;
        //return "[ POST ] Poll with id=" + pid;
    }

    @GetMapping("/polls/{pid}/{field}")
    /**
     * View poll with given id.
     */
    public String getPollField(@PathVariable("pid") Integer pid, @PathVariable("field") String field) {
        return "[ GET ] Poll with id=" + pid + " and field " + field;
    }

    @DeleteMapping("/polls/{pid}")
    public Polls deletePoll(@PathVariable("pid") Long pid) {
        polls.deletePoll(pid);
        return polls;
        //return "[ DELETE ] Deleting poll with id=" + pid;
    }

    @PutMapping("/polls/{pid}")
    public Polls putPoll(@PathVariable("pid") Long pid, @RequestBody Poll poll) {

        Poll currPoll = polls.getPollById(pid);

        // if the given poll id doesn't correspond to an existing poll.
        if (currPoll == null) {

            polls.add(new Poll(pid,poll.getTitle(),poll.getDeadline(),poll.isPublic()));
            return polls;
        }

        currPoll.setTitle(poll.getTitle());
        currPoll.setDeadline(poll.getDeadline());
        currPoll.setPublic(poll.isPublic());


        return polls;
//        return "[ PUT ] Poll with id=" + pid + " replaced by new poll: " + poll.toString();
    }

    @PutMapping("/polls/{pid}/{field}")
    public String putPoll(@PathVariable("pid") Integer pid, @PathVariable("field") String field, @RequestBody Object newValue) {
        switch (field) {
            case "title":
                return "[ PUT ] Updating title to " + (String) newValue;

            case "deadline":
                return "[ PUT ] Updating deadline.";

            case "public":
                return "[ PUT ] Updating poll with id " + pid + " to public=" + (Boolean) newValue;

            default:
                return "Did not understand 'field'";
        }
    }

    @GetMapping("/polls/{pid}/votes")
    /**
     * View votes on a poll with given poll-id.
     */
    public String getPollVotes(@PathVariable("pid") Integer pid) {
        return "[ GET ] Votes on poll with id=" + pid;
    }

    @DeleteMapping("/polls/{pid}/votes")
    /**
     * View votes on a poll with given poll-id.
     */
    public String deletePollVotes(@PathVariable("pid") Integer pid) {
        return "[ GET ] Deleting votes on poll with id=" + pid;
    }

    @GetMapping("/polls/{pid}/votes/{vid}")
    /**
     * View vote on a given poll.
     */
    public String getPollVote(@PathVariable("pid") Integer pid) {
        return "[ GET ] Votes on poll with id=" + pid;
    }

    @DeleteMapping("/polls/{pid}/votes/{vid}")
    /**
     * View vote on a given poll.
     */
    public String deletePollVote(@PathVariable("pid") Integer pid, @PathVariable("vid") Integer vid) {
        return "[ GET ] Deleting votes on poll with id=" + pid;
    }

    @PutMapping("/polls/{pid}/votes/{vid}")
    public String putPollVote(@PathVariable("pid") Integer pid, @PathVariable("vid") Integer vid, @RequestBody Object newValue) {
        return "[ PUT ] Updated poll with id" + vid;//(Vote) newValue;
    }

    @PostMapping("/polls/{pid}/votes/{vid}")
    public String postPollVote(@PathVariable("pid") Integer pid, @PathVariable("vid") Integer vid, @RequestBody Object newValue) {
        return "[ POST ] Posted poll with id" + vid;//(Vote) newValue;
    }

    @PutMapping("/polls/{pid}/votes/{vid}/{field}")
    public String putPollVoteField(@PathVariable("pid") Integer pid, @PathVariable("vid") Integer vid, @PathVariable("field") Integer field, @RequestBody Object newValue) {
        if (field.equals("num_yes")) {
            return "[ PUT ] Updated `num_yes` on vote " + vid;//(Vote) newValue;
        } else if (field.equals("num_no")) {
            return "[ PUT ] Updated `num_no` on vote " + vid;
        }
        return "[ PUT ] Failed to update vote on poll " + pid;//(Vote) newValue;
    }
}