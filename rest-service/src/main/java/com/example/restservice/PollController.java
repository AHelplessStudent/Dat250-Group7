package com.example.restservice;

import com.example.restservice.exception.FieldNotFound;
import com.example.restservice.exception.PollNotFound;
import com.example.restservice.repository.PollRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PollController {

    private PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping("/polls")
    public List<Poll> allPolls() {
        return pollRepository.findAll();
    }

    @GetMapping("/polls/{pid}")
    public Poll onePoll(@PathVariable("pid") Long pid) {
        return pollRepository.findById(pid).orElseThrow(() -> new PollNotFound(pid));
    }

    @GetMapping("/polls/{pid}/{field}")
    public Object getPollField(@PathVariable("pid") Long pid, @PathVariable("field") String field) {
        Poll poll = pollRepository.findById(pid).orElseThrow(() -> new PollNotFound(pid));

        switch (field) {
            case "title":
                return poll.getTitle();

            case "deadline":
                return poll.getDeadline();

            case "isPublic":
                return poll.isPublic();

            default:
                throw new FieldNotFound(field);
        }
    }

    @GetMapping("/polls/{pid}/votes")
    public List<Vote> allPollVotes(@PathVariable("pid") Long pid) {
        return pollRepository.findById(pid)
                .orElseThrow(() -> new PollNotFound(pid))
                .getVotes();
    }

    /* TODO
    @GetMapping("/polls/{pid}/votes/{vid}")
    public Vote getPollVote(@PathVariable(value = "pid", required = false) Long pid, @PathVariable("vid") Long vid) {
        return polls.getPollById(pid).getVotes().getVoteById(vid);
    }
    */

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping("/polls")
    public Poll postPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    /*
    @PostMapping("/polls/{pid}/votes")
    public Poll postPollVote(@PathVariable("pid") Long pid, @RequestBody Vote newValue) {

        Poll p = polls.getPollById(pid);

        newValue.setPollId(p.getPollId());
        p.getVotes().add(newValue);

        return p;
    }
    */

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("/polls")
    public void deletePolls() {
        pollRepository.deleteAll();
    }

    @DeleteMapping("/polls/{pid}")
    public void deletePoll(@PathVariable("pid") Long pid) {
        pollRepository.deleteById(pid);
    }

    /*
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

     */

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("/polls/{pid}")
    public Poll replacePoll(@RequestBody Poll newPoll, @PathVariable("pid") Long pid) {
        return pollRepository.findById(pid)
                .map(poll -> {
                    poll.setDeadline(newPoll.getDeadline());
                    poll.setPublic(newPoll.isPublic());
                    poll.setTitle(newPoll.getTitle());
                    return pollRepository.save(poll);
                })
                .orElseGet(() -> {
                    newPoll.setPollId(pid);
                    return pollRepository.save(newPoll);
                });
    }

    /*
    @PutMapping("/polls/{pid}/votes/{vid}")
    public Poll putPollVote(@PathVariable("pid") Long pid, @PathVariable("vid") Long vid, @RequestBody Vote newValue) {
        Poll poll = polls.getPollById(pid);
        Vote currVote = poll.getVotes().getVoteById(vid);

        if (currVote == null) {
            poll.getVotes().add(newValue);
        }

        currVote.setNum_yes(newValue.getNum_yes());
        currVote.setNum_no(newValue.getNum_no());

        return poll;
    }
     */
}