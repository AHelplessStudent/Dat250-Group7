package no.group7.restservice.messaging;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    private Logger logger = LoggerFactory.getLogger(MessageComponent.class);

    /**
     * For production, we should probably have a separate application for this.
     * It will suffice for this project.
     */
    @Scheduled(fixedDelay = 1000)
    public void publishFinishedPolls() {
        int numFinishedPolls = 0;

        for (Poll poll : pollRepository.findAll()) {
            if (poll.isExpired()) {
                numFinishedPolls++;
            }
        }

        if (numFinishedPolls > 0)
            logger.info(numFinishedPolls + " finished polls found => sending to RabbitMQ!");
    }
}