package com.example.restservice;

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
    public String votes() {
        return "Here is all the votes";
    }

    @GetMapping("/votes/{vid}")
    /**
     * View vote with given id.
     */
    public String viewVote(@PathVariable("vid") Integer vid) {
        return "Vote with id=" + vid;
    }
}