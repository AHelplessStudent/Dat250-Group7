package no.group7.restservice;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.entity.Vote;
import no.group7.restservice.repository.PollRepository;
import no.group7.restservice.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class RestServiceApplication {
	private static final Logger log = LoggerFactory.getLogger(RestServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PollRepository pollRepository, VoteRepository voteRepository) {
		return (args) -> {
			pollRepository.save(new Poll("First Poll",null,true));
			pollRepository.save(new Poll("Sports Poll",null,true));
			pollRepository.save(new Poll("Music Poll",null,false));

			// fetch all customers
			log.info("Polls found with findAll():");
			log.info("-------------------------------");
			for (Poll poll : pollRepository.findAll()) {
				log.info(poll.toString());
			}
			log.info("");

			Optional<Poll> poll = pollRepository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(poll.toString());
			log.info("");


		};
	}

}
