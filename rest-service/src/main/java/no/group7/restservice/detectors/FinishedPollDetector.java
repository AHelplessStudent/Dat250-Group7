package no.group7.restservice.detectors;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

import static no.group7.restservice.dweet.DweetCommunicator.sendPollToDweet;
import static no.group7.restservice.messaging.RabbitMQCommunicator.sendPollToRabbitMQ;

@Component
public class FinishedPollDetector {

    @Autowired
    private PollRepository pollRepository;

    private HashSet<Long> sentPollsId = new HashSet<>();

    /**
     * Scan and check for finished polls.
     * <p>
     * If finished polls found => send it through RabbitMQ.
     */
    @Scheduled(fixedDelay = 10000)
    public void publishFinishedPolls() {
        for (Poll poll : pollRepository.findAll()) {
            if (poll.getEndTime() == null)
                continue;  // debug
            if (sentPollsId.contains(poll.getId())) { // ignore already sent polls
                continue;
            }

            boolean pollExpired = LocalDateTime.now().isAfter(poll.getEndTime());
            if (pollExpired) {
                System.out.println("Expired poll discovered!");
                sentPollsId.add(poll.getId());

                sendPollToDweet(poll);
                sendPollToRabbitMQ(poll);
            }
        }
    }
}
