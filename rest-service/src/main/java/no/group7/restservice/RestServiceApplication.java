package no.group7.restservice;

import no.group7.restservice.entity.Account;
import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.entity.VoteCompositeKey;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class RestServiceApplication {

    /* QUICK-TOGGLE */
    public static boolean USE_RABBITMQ = false;

    private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PollRepository pollRepository, VoteRepository voteRepository, AccountRepository accountRepository) {
        return (args) -> {

            // First, create Account
            Account account = new Account();
            account.setFirstName("Test-Firstname");
            account.setLastName("Test-Lastname");
            account.setUsername("Test-Username");
            account.setAuthId("ID");
            accountRepository.save(account);

            // Then, create poll
            Poll poll = new Poll();
            poll.setTitle("Interesting Title");
            poll.setQuestion("Is this true?");
            poll.setEndTime(LocalDateTime.now().plusSeconds(10));
            poll.setAccount(account);
            poll.setNum_no(10);
            poll.setNum_yes(25);
            pollRepository.save(poll);

            // Last, create a vote
            VoteCompositeKey voteCompositeKey = new VoteCompositeKey();
            voteCompositeKey.setAccountId(account.getId());
            voteCompositeKey.setPollId(poll.getId());

            Vote vote1 = new Vote();
            vote1.setAccount(account);
            vote1.setPoll(poll);
            vote1.setVotedYes(true);
            vote1.setId(voteCompositeKey);
            voteRepository.save(vote1);

            log.info("Polls found with findAll():");
            log.info("-------------------------------");
            for (Poll p : pollRepository.findAll()) {
                log.info(p.toString());
            }
            log.info("");

            log.info("Votes found with findAll():");
            log.info("-------------------------------");
            for (Vote v : voteRepository.findAll()) {
                log.info(v.toString());
            }
            log.info("");

            log.info("Accounts found with findAll():");
            log.info("-------------------------------");
            for (Account a : accountRepository.findAll()) {
                log.info(a.toString());
            }
            log.info("");
        };
    }
}
