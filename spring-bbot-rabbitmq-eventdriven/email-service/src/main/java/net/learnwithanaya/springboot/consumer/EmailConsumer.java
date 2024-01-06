package net.learnwithanaya.springboot.consumer;

import net.learnwithanaya.springboot.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = "${spring.queue.email.name}")
    public void consume(OrderEvent orderEvent){
        logger.info(String.format("Order details consumed in Email Q%s", orderEvent.toString()));

        //Save data into database
    }
}
