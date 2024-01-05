package net.learnwithanaya.springboot.publisher;

import net.learnwithanaya.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    @Value("${rabbitmq.routing.json.key}")
    String jsonRoutingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        logger.info(String.format("Json message produced %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }
}
