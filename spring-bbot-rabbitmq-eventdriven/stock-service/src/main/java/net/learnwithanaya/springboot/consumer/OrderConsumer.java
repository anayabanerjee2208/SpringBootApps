package net.learnwithanaya.springboot.consumer;

import net.learnwithanaya.springboot.model.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = "${spring.queue.order.name}")
    public void consume(OrderEvent orderEvent){
        logger.info(String.format("Order details consumed %s", orderEvent.toString()));

        //Save data into database
    }
}
