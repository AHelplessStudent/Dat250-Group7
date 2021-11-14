/**
 * RabbitMQ-code from: https://spring.io/guides/gs/messaging-rabbitmq/
 * (now modified, website read 09.11.2021)
 */
package no.group7.restservice.messaging;

/*
@Component
public class MessageComponent {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(MessageComponent.class);

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


    @Scheduled(fixedDelay = 10000)
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
                    "",
                    numFinishedPolls + " finished polls found"
            );
            logger.info(numFinishedPolls + " finished polls found => sending to RabbitMQ!");
        }
    }

}*/