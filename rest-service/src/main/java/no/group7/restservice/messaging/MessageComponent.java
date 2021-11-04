package no.group7.restservice.messaging;

import no.group7.restservice.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    /**
     * For production, we should probably have a separate application for this.
     * It will suffice for this project.
     */
    @Scheduled(fixedDelay = 1000)
    public void publishFinishedPolls() {
        System.out.println("Checking for finished polls...");
        pollRepository.findById(1L);
    }
}