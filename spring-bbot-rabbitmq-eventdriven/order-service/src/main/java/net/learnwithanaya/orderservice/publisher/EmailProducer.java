package net.learnwithanaya.orderservice.publisher;

import net.learnwithanaya.orderservice.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailProducer {
    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${spring.exchange.name}")
    private String exchange;
    @Value("${spring.routingkey.email.name}")
    private String emailKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        logger.info(String.format("Order event was sent to Email Q %s", orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, emailKey, orderEvent);
    }
}
