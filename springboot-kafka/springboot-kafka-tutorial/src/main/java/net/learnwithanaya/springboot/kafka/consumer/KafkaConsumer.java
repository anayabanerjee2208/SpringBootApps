package net.learnwithanaya.springboot.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    //Subscriber method which will subscribe to the topic
    @KafkaListener(topics ="${spring.kafka.topic.name}", groupId = "${spring.kafka.topic.groupName}") //listen or subscribe to the topic
    public void consume(String message){
        LOGGER.info(String.format("Message Received %s", message));
    }
}
