package com.example.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
/**
 * Controller for REST-api starting with '/votes'.
 */
public class VoteController {

    /**
     * TODO: change return types on the methods (not all should be String).
     * This class is unfinished.
     */

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/votes")
    public String getVotes() {
        return "[ GET ]Here is all the votes";
    }

    @GetMapping("/votes/{vid}")
    /**
     * View vote with given id.
     */
    public String getVote(@PathVariable("vid") Integer vid) {
        return "[ GET ]Vote with id=" + vid;
    }

    @DeleteMapping("/votes/{vid}")
    /**
     * View vote with given id.
     */
    public String deleteVote(@PathVariable("vid") Integer vid) {
        return "[ GET ] Deleting vote with id=" + vid;
    }

    @PutMapping("/votes/{vid}")
    public String putVote(@PathVariable("vid") Integer vid, @RequestBody Object newValue) {
        return "[ PUT ] Updated vote with id" + vid;//(Vote) newValue;
    }

    @PostMapping("/votes/{vid}")
    public String postVote(@PathVariable("vid") Integer vid, @RequestBody Object newValue) {
        return "[ POST ] Post vote with id" + vid;//(Vote) newValue;
    }

    @PutMapping("/votes/{vid}/{field}")
    public String putVoteField(@PathVariable("vid") Integer vid, @PathVariable("field") String field, @RequestBody Object newValue) {
        if (field.equals("num_yes")) {
            return "[ PUT ] Updated `num_yes` on vote " + vid;//(Vote) newValue;
        } else if (field.equals("num_no")) {
            return "[ PUT ] Updated `num_no` on vote " + vid;
        }
        return "[ PUT ] Failed to update vote with id= " + vid;//(Vote) newValue;
    }
}