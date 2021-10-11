package no.group7.restservice.controller;

import no.group7.restservice.entity.Vote;
import no.group7.restservice.exception.VoteNotFound;
import no.group7.restservice.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteRepository voteRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public List<Vote> allVotes() {
        return voteRepository.findAll();
    }

    @GetMapping("{vid}")
    public Vote oneVote(@PathVariable(value = "vid") Long vid) {
        return voteRepository.findById(vid).orElseThrow(() -> new VoteNotFound(vid));
    }

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("{pid}")
    public void deleteVote(@PathVariable("vid") Long vid) {
        voteRepository.deleteById(vid);
    }

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public Vote postVote(@RequestBody Vote vote) {
        return voteRepository.save(vote);
    }

    //////////////////////////////////////
    //// PUT-REQUESTS                 ////
    //////////////////////////////////////
    @PutMapping("{vid}")
    public Vote replaceVote(@PathVariable("vid") Long vid, @RequestBody Vote newVote) {
        return voteRepository.findById(vid)
                .map(vote -> {
                    vote.setNum_no(newVote.getNum_no());
                    vote.setNum_yes(newVote.getNum_yes());
                    return voteRepository.save(vote);
                })
                .orElseGet(() -> {
                    newVote.setVoteId(vid);
                    return voteRepository.save(newVote);
                });
    }
}