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
import org.springframework.stereotype.Component;

@Component
public class RabbitMQCommunicator {

    @Autowired
    private PollRepository pollRepository;


    private static RabbitTemplate rabbitTemplate;


    @Autowired
    /**
     * Thanks to following for the solution: https://stackoverflow.com/a/5991240
     * Original author (from stackoverflow): Sedat Başar
     * (read 16.11.2021)
     */
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMQCommunicator.rabbitTemplate = rabbitTemplate;
    }

    private static Logger logger = LoggerFactory.getLogger(RabbitMQCommunicator.class);

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

    public static void sendPollToRabbitMQ(Poll poll) {
        if (!RestServiceApplication.USE_RABBITMQ)
            return;

        try {
            String pollJson = "{ \"id\":" + poll.getId() +
                    ", \"title\":\"" + poll.getTitle() + "\"" +
                    ", \"question\":\"" + poll.getQuestion() + "\"" +
                    ", \"startTime\":\"" + poll.getStartTime() + "\"" +
                    ", \"endTime\":\"" + poll.getEndTime() + "\"" +
                    ", \"numYes\":" + poll.getNum_yes() +
                    ", \"numNo\":" + poll.getNum_no() +
                    ", \"isClosed\":" + poll.isClosed() +
                    ", \"isPublic\":" + poll.isPublic() +
                    " }";
            System.out.println(pollJson);
            // send to RabbitMQ
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NAME,
                    "",
                    pollJson
            );
            logger.info("<RabbitMQ> Sending poll through RabbitMQ:");
            logger.info("    " + pollJson);
            logger.info("");
        } catch (NullPointerException e) {
            logger.error("<RabbitMQ> Could not send poll through RabbitMQ (most likely some fields were null / not enough info)");
        }
    }
}