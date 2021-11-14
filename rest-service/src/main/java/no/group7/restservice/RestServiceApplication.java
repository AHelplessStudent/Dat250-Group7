package no.group7.restservice;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.repository.AccountRepository;
import no.group7.restservice.repository.PollRepository;
import no.group7.restservice.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class RestServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PollRepository pollRepository, VoteRepository voteRepository, AccountRepository accountRepository) {
        return (args) -> {

            Poll generic_poll = new Poll("Politics Poll", "Question here", LocalDateTime.now(), LocalDateTime.now(), true, 0, 0);
            pollRepository.save(generic_poll);

            log.info("Polls found with findAll():");
            log.info("-------------------------------");
            for (Poll poll : pollRepository.findAll()) {
                log.info(poll.toString());
            }
            log.info("");
        };
    }
}
