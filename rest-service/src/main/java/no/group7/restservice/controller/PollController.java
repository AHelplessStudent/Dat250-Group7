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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static no.group7.restservice.dweet.DweetCommunicator.sendPollToDweet;


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

    @GetMapping("/auth_id/{auth_id}")
    public ResponseEntity<List<Poll>> allPollVotesByAuthId(@PathVariable("auth_id") String auth_id) {
        List<Poll> polls = pollRepository.findAll();
        List<Poll> pollsByAuthId = new ArrayList<>();

        for (Poll poll : polls) {
            try {
                if (poll.getAccount().getAuthId().equals(auth_id))
                    pollsByAuthId.add(poll);
            } catch (NullPointerException e) {
                continue;  //ignore
            }
        }

        return new ResponseEntity<>(pollsByAuthId, HttpStatus.OK);
    }

    // TODO: add get for specific vote {pid}/votes/{vid}


    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public ResponseEntity<Poll> postPoll(@RequestBody Poll poll) throws IOException {
        ResponseEntity<Poll> pollResponseEntity = new ResponseEntity<>(pollRepository.save(poll), HttpStatus.OK);
        sendPollToDweet(poll);

        return pollResponseEntity;
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

    @PatchMapping("/voteYes/{id}")
    public ResponseEntity<Poll> voteYes(@PathVariable Long id) {
        try {
            Poll poll = pollRepository.findById(id).get();

            if (poll.isClosed())
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            poll.setNum_yes(poll.getNum_yes() + 1);
            return new ResponseEntity<Poll>(pollRepository.save(poll), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/voteNo/{id}")
    public ResponseEntity<Poll> voteNo(@PathVariable Long id) {
        try {
            Poll poll = pollRepository.findById(id).get();

            if (poll.isClosed())
                return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
            poll.setNum_no(poll.getNum_no() + 1);
            return new ResponseEntity<Poll>(pollRepository.save(poll), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/expire/{id}")
    public ResponseEntity<Poll> expire(@PathVariable Long id) {
        try {
            Poll poll = pollRepository.findById(id).get();
            poll.setClosed(true);
            sendPollToDweet(poll);
            return new ResponseEntity<>(pollRepository.save(poll), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}