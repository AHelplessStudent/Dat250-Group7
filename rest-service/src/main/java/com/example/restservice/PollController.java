package com.example.restservice;

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

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/polls")
    public String polls() {
        return "Here is all the polls";
    }

    @GetMapping("/polls/{pid}")
    /**
     * View poll with given id.
     */
    public String viewPoll(@PathVariable("pid") Integer pid) {
        return "Poll with id=" + pid;
    }

    @GetMapping("/polls/{pid}/votes")
    /**
     * View votes on a poll with given poll-id.
     */
    public String viewPollVotes(@PathVariable("pid") Integer pid) {
        return "Votes on poll with id=" + pid;
    }

    @GetMapping("/polls/{pid}/votes/{vid}")
    /**
     * View vote on a given poll.
     */
    public String viewPollVote(@PathVariable("pid") Integer pid) {
        return "Votes on poll with id=" + pid;
    }
}