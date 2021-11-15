package no.group7.restservice.controller;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.repository.AccountRepository;
import no.group7.restservice.repository.PollRepository;
import no.group7.restservice.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private VoteRepository voteRepository;


    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public ResponseEntity<List<Poll>> getAllPolls() {
        return new ResponseEntity<>(pollRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Poll> onePoll(@PathVariable("id") Long id) {
        Optional<Poll> poll = pollRepository.findById(id);

        try {
            return new ResponseEntity<>(poll.get(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{id}/votes")
    public ResponseEntity<List<Vote>> allPollVotes(@PathVariable("id") Long id) {
        Optional<Poll> poll = pollRepository.findById(id);

        try {
            return new ResponseEntity<>(poll.get().getVotes(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // TODO: add get for specific vote {pid}/votes/{vid}

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public ResponseEntity<Poll> postPoll(@RequestBody Poll poll) {
        return new ResponseEntity<>(pollRepository.save(poll), HttpStatus.OK);
    }

    @PostMapping("{id}/votes")
    public ResponseEntity<Vote> postPollVote(@PathVariable("id") Long id, @RequestBody Vote vote) {
        Optional<Poll> optionalPoll = pollRepository.findById(id);

        try {
            vote.setAccount(accountRepository.getById(vote.getId().getAccountId()));
            vote.setPoll(optionalPoll.get());

            return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("{pid}")
    public void deletePoll(@PathVariable("pid") Long pid) {
        pollRepository.deleteById(pid);
    }

    // TODO: add delete for specific vote {pid}/votes/{vid}
    /*
    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("{pid}")
    public Poll replacePoll(@RequestBody Poll newPoll, @PathVariable("pid") Long pid) {
        // does not reset the votes.
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

    ostPollVote(@PathVariable("pid") Long pid, @RequestBody Vote vote) {
        return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
    }

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("{pid}")
    public Poll replacePoll(@RequestBody Poll newPoll, @PathVariable("pid") Long pid) {
        // does not reset the votes.
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
    }*/
}