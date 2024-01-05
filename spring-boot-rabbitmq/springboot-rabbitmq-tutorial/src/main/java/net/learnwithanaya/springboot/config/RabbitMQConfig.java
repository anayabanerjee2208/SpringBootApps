package net.learnwithanaya.springboot.config;

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

    //Spring bean for RabbitMQ queue
    @Value("${rabbitmq.queue.name}")
    String queueName;

    @Value("${rabbitmq.exchange.name}")
    String exchangeName;


    @Value("${rabbitmq.routing.key}")
    String routingKey;

    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }

    //Binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
       return BindingBuilder
               .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
//Below 3 are infrastructure beans which will be autoconfigured by Springboot
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin

    //Define queue for Json messages
    @Value("${rabbitmq.queue.jsonqueue}")
    private String jsonQueue;

    @Value("${rabbitmq.routing.json.key}")
    String routingKeyJson;
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public Binding jsonBining(){
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingKeyJson);
    }

    //Define MessageConverter for JsonConverter
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    //AmqpTemplate is an interface and RabbitTemplate implements AmqpTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
