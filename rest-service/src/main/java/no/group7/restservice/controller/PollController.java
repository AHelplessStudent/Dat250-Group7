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

    @GetMapping("{id}")
    public Poll onePoll(@PathVariable() Long id) {
        return pollRepository.findById(id).orElseThrow(() -> new PollNotFound(id));
    }

    @GetMapping("{id}/{field}")
    public Object getPollField(@PathVariable("id") Long id, @PathVariable("field") String field) {
        Poll poll = pollRepository.findById(id).orElseThrow(() -> new PollNotFound(id));

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

    @GetMapping("{id}/votes")
    public List<Vote> allPollVotes(@PathVariable("id") Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new PollNotFound(id))
                .getVotes();
    }

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public Poll postPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }


    @PostMapping("{pid}/votes")
    public Vote postPollVote(@PathVariable("pid") Long pid, @RequestBody Vote newValue) {
        Poll p = pollRepository.getById(pid);

        p.getVotes().add(newValue);

        newValue.setPoll(p);

        pollRepository.save(p);

        return newValue;
    }
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