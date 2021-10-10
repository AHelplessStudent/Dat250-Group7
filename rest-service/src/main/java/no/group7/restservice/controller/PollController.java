package no.group7.restservice.controller;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.exception.FieldNotFound;
import no.group7.restservice.exception.PollNotFound;
import no.group7.restservice.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public List<Poll> allPolls() {
        return pollRepository.findAll();
    }

    @GetMapping("{pid}")
    public Poll onePoll(@PathVariable("pid") Long pid) {
        return pollRepository.findById(pid).orElseThrow(() -> new PollNotFound(pid));
    }

    @GetMapping("{pid}/{field}")
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

    @GetMapping("{pid}/votes")
    public List<Vote> allPollVotes(@PathVariable("pid") Long pid) {
        return pollRepository.findById(pid)
                .orElseThrow(() -> new PollNotFound(pid))
                .getVotes();
    }

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public Poll postPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    /* TODO
    @PostMapping("{pid}/votes")
    public Vote postPollVote(@PathVariable("pid") Long pid, @RequestBody Vote newValue) {
        ...
    }
    */

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping()
    public void deletePolls() {
        pollRepository.deleteAll();
    }

    @DeleteMapping("{pid}")
    public void deletePoll(@PathVariable("pid") Long pid) {
        pollRepository.deleteById(pid);
    }

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("{pid}")
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
}