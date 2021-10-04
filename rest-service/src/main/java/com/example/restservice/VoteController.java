package com.example.restservice;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
/**
 * Controller for REST-api starting with '/votes'.
 */
public class VoteController {

    /**
     * TODO: change return types on the methods (not all should be String)
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
}