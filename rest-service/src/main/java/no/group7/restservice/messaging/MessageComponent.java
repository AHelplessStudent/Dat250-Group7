/**
 * RabbitMQ-code from: https://spring.io/guides/gs/messaging-rabbitmq/
 * (now modified, website read 09.11.2021)
 */
package no.group7.restservice.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import no.group7.restservice.entity.Poll;
import no.group7.restservice.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(MessageComponent.class);
    private LocalDateTime analyzerStarted;  // When the analyzer started => only check for polls expired after this!
    private ArrayList<Poll> sentPolls = new ArrayList<>();

    // Constants
    public final static String EXCHANGE_NAME = "default_exchange_name";
    public final static String QUEUE_NAME_1 = "fan1";
    public final static String QUEUE_NAME_2 = "fan2";

    @PostConstruct
    public void setTimeNow() {
        analyzerStarted = LocalDateTime.now();
    }

    @Bean
    Queue queue1() {
        return new Queue(QUEUE_NAME_1, false);
    }

    @Bean
    Queue queue2() {
        return new Queue(QUEUE_NAME_2, false);
    }

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding bindingQueue1(Queue queue1, FanoutExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange);
    }

    @Bean
    Binding bindingQueue2(Queue queue2, FanoutExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME_1, QUEUE_NAME_2);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    /**
     * For production, we should probably have a separate application for this.
     * It will suffice for this project.
     */
    @Scheduled(fixedDelay = 10000)
    public void publishFinishedPolls() throws JsonProcessingException {
        for (Poll poll : pollRepository.findAll()) {
            if (sentPolls.contains(poll)) {
                continue;
            }
            boolean pollExpiredAfterClassStart = poll.isExpired() && analyzerStarted.isBefore(poll.getDeadline());

            if (pollExpiredAfterClassStart) {
                String pollInJSON = "{ 'title': \"" + poll.getTitle() + ", 'id': " + poll.getPollId() + " }";
                logger.info("Finished poll, sending to RabbitMQ: " + pollInJSON + analyzerStarted);
                sentPolls.add(poll);
                rabbitTemplate.convertAndSend(
                        EXCHANGE_NAME,
                        "",
                        pollInJSON
                );
            }
        }
    }
}