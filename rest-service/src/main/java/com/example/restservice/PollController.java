package com.example.restservice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
/**
 * Controller for REST-api starting with '/polls'.
 */
public class PollController {

    /**
     * TODO: change return types on the methods (not all should be String)
     */
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/polls")
    public String getPolls() {
        return "[ GET ] Here is all the polls";
    }

    @DeleteMapping("/polls")
    public String deletePolls() {
        return "[ DELETE ] Deleting all polls";
    }


    @GetMapping("/polls/{pid}")
    /**
     * View poll with given id.
     */
    public String getPoll(@PathVariable("pid") Integer pid) {
        return "[ GET ] Poll with id=" + pid;
    }

    @DeleteMapping("/polls/{pid}")
    public String deletePoll(@PathVariable("pid") Integer pid) {
        return "[ DELETE ] Deleting poll with id=" + pid;
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
    public String deletePollVote(@PathVariable("pid") Integer pid) {
        return "[ GET ] Deleting votes on poll with id=" + pid;
    }
}