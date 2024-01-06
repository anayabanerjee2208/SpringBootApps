package net.learnwithanaya.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    //spring bean for queue

    @Value("${spring.queue.order.name}")
    private String orderQueue;
    @Value("${spring.exchange.name}")
    private String exchange;
    @Value("${spring.routingkey.order.name}")
    private String orderKey;

    @Value("${spring.queue.email.name}")
    private String emailQueue;

    @Value("${spring.routingkey.email.name}")
    private String emailKey;

    @Bean
    public Queue orderQueue(){
        return new Queue(orderQueue);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(orderQueue())
                .to(exchange())
                .with(orderKey);
    }

    @Bean
    public Binding bindingEmail(){
        return BindingBuilder.bind(emailQueue())
                .to(exchange())
                .with(emailKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
