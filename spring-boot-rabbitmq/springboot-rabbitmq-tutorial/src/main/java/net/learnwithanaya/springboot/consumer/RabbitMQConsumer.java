package net.learnwithanaya.springboot.consumer;

import net.learnwithanaya.springboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consume(String message){
        logger.info(String.format("Message received => %s", message));
    }

    @RabbitListener(queues = "${rabbitmq.queue.jsonqueue}")
    public void consumeJsonMessage(User user){
        logger.info(String.format("Json message received %s", user.toString()));
    }
}
