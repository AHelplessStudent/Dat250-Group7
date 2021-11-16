/**
 * RabbitMQ-code from: https://spring.io/guides/gs/messaging-rabbitmq/
 * (now modified, website read 09.11.2021)
 */
package no.group7.restservice.messaging;

import no.group7.restservice.RestServiceApplication;
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

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(MessageComponent.class);

    private HashSet<Long> sentPollsId = new HashSet<>();

    // Constants
    public final static String EXCHANGE_NAME = "default_exchange_name";
    public final static String QUEUE_NAME_1 = "fan1";
    public final static String QUEUE_NAME_2 = "fan2";

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
     * Scan and check for finished polls.
     * <p>
     * If finished polls found => send it through RabbitMQ.
     */
    @Scheduled(fixedDelay = 10000)
    public void publishFinishedPolls() {
        if (!RestServiceApplication.USE_RABBITMQ)
            return;
        Poll poll1 = pollRepository.findById(1L).get();
        // debug
        rabbitTemplate.convertAndSend(
                EXCHANGE_NAME,
                "",
                "{ \"id\":" + poll1.getId() +
                        ", \"num_yes\":" + poll1.getNum_yes() +
                        ", \"num_no\":" + poll1.getNum_no() +
                        " }"
        );

        for (Poll poll : pollRepository.findAll()) {
            if (poll.getEndTime() == null)
                continue;  // debug
            if (sentPollsId.contains(poll.getId())) { // ignore already sent polls
                continue;
            }
            boolean pollExpired = LocalDateTime.now().isAfter(poll.getEndTime());
            if (pollExpired) {
                sentPollsId.add(poll.getId());

                String pollJson = "{ \"id\":" + poll.getId() +
                        ", \"num_yes\":" + poll.getNum_yes() +
                        ", \"num_no\":" + poll.getNum_no() +
                        " }";

                // send to RabbitMQ
                rabbitTemplate.convertAndSend(
                        EXCHANGE_NAME,
                        "",
                        pollJson
                );
                logger.info("Finished poll discovered => sending through RabbitMQ:");
                logger.info("    " + pollJson);
            }
        }
    }
}