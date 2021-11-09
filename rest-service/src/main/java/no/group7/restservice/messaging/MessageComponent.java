/**
 * RabbitMQ-code from: https://spring.io/guides/gs/messaging-rabbitmq/
 * (website read 09.11.2021)
 */
package no.group7.restservice.messaging;

import no.group7.restservice.entity.Poll;
import no.group7.restservice.repository.PollRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(MessageComponent.class);

    // Constants
    public final static String EXCHANGE_NAME = "default_exchange_name";
    public final static String QUEUE_NAME = "poll_results";
    public final static String ROUTING_KEY = "foo.bar.#";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_NAME);
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
    @Scheduled(fixedDelay = 1000)
    public void publishFinishedPolls() {
        int numFinishedPolls = 0;

        for (Poll poll : pollRepository.findAll()) {
            if (poll.isExpired()) {
                numFinishedPolls++;
            }
        }

        if (numFinishedPolls > 0) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NAME,
                    "foo.bar.baz",
                    numFinishedPolls + " finished polls found"
            );
            logger.info(numFinishedPolls + " finished polls found => sending to RabbitMQ!");
        }
    }
}