package no.group7.restservice.controller;

import no.group7.restservice.DTO.MaptoDTO;
import no.group7.restservice.DTO.PollDTO;
import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private MaptoDTO maptoDTO;

    @Autowired
    private PollRepository pollRepository;

    //////////////////////////////////////
    //// GET-REQUESTS                 ////
    //////////////////////////////////////
    @GetMapping()
    public Collection<PollDTO> allPolls() {
        return
    }

    @GetMapping("{id}")
    public PollDTO onePoll(@PathVariable() Long id) {

        return maptoDTO.getPollById(id);
        //return pollRepository.findById(id).orElseThrow(() -> new PollNotFound(id));
    }

    @GetMapping("{id}/votes")
    public int[] allPollVotes(@PathVariable("id") Long id) {
        PollDTO p = maptoDTO.getPollById(id);

        return new int[]{p.getNum_no(), p.getNum_yes()};
    }

    // TODO: add get for specific vote {pid}/votes/{vid}

    //////////////////////////////////////
    //// POST-REQUESTS                ////
    //////////////////////////////////////
    @PostMapping()
    public Poll postPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    @PostMapping("{pid}/votes")
    public Poll postPollVote(@PathVariable("pid") Long pid, @RequestBody Vote newValue) {
        Poll p = pollRepository.findById(pid).get();

        newValue.setPoll(p);
        p.getVotes().add(newValue);


        pollRepository.save(p);

        return p;
    }

    //////////////////////////////////////
    //// DELETE-REQUESTS              ////
    //////////////////////////////////////
    @DeleteMapping("{pid}")
    public void deletePoll(@PathVariable("pid") Long pid) {
        pollRepository.deleteById(pid);
    }

    // TODO: add delete for specific vote {pid}/votes/{vid}

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
}