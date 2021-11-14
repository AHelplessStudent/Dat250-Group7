package no.group7.restservice;

import no.group7.restservice.entity.Account;
import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.repository.AccountRepository;
import no.group7.restservice.repository.PollRepository;
import no.group7.restservice.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.scheduling.annotation.EnableScheduling;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class RestServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(PollRepository pollRepository, VoteRepository voteRepository, AccountRepository accountRepository) {
        return (args) -> {

            Poll poll1 = new Poll("Politics Poll", LocalDateTime.now(), LocalDateTime.now(), true);
            Poll sports_poll = new Poll("Sports Poll", LocalDateTime.now(), LocalDateTime.now(), true);
            Poll music_poll = new Poll("Music Poll", LocalDateTime.now(), LocalDateTime.now(), false);

            Vote vote1 = new Vote(10, 8);
            Vote vote2 = new Vote(10, 8);
            Vote vote3 = new Vote(10, 8);
            vote1.setPoll(poll1);
            vote2.setPoll(sports_poll);
            vote3.setPoll(music_poll);

            voteRepository.save(vote1);
            voteRepository.save(vote2);
            voteRepository.save(vote3);

            Account acc1 = new Account("James101", "hashedsaltedpsw", "Test", "Testson");
            accountRepository.save(acc1);

            poll1.setAccount(acc1);
            poll1.getVotes().add(vote1);
            pollRepository.save(poll1);


            sports_poll.setAccount(acc1);
            sports_poll.getVotes().add(vote2);
            pollRepository.save(sports_poll);

            music_poll.setAccount(acc1);
            music_poll.getVotes().add(vote3);
            pollRepository.save(music_poll);

            Collection<Poll> polls = new ArrayList<>();
            polls.add(poll1);
            polls.add(sports_poll);
            polls.add(music_poll);
            acc1.setPolls(polls);
            accountRepository.save(acc1);

            // fetch all customers
            log.info("Polls found with findAll():");
            log.info("-------------------------------");
            for (Poll poll : pollRepository.findAll()) {
                log.info(poll.toString());
            }
            log.info("");

            Optional<Poll> poll = pollRepository.findById(1L);
            log.info("Poll found with findById(1L):");
            log.info("--------------------------------");
            log.info(poll.toString());
            log.info("");

            log.info("Poll 1");
            log.info("--------------------------------");
            log.info(poll1.toString());
            log.info(poll1.getAccount().toString());
            log.info(sports_poll.getAccount().toString());
            log.info(music_poll.getAccount().toString());
        };
    }


}
