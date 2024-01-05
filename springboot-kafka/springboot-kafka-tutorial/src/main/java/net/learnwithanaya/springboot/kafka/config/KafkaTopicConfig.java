package net.learnwithanaya.springboot.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration // so that this class acts as a java configuration class
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.topic-json.name}")
    private String jsonTopicName;

    @Bean
    public NewTopic learnWithAnayaTopic(){
       // return TopicBuilder.name("learnwithanaya")
         //       .partitions(10).replicas(10).build();

        return TopicBuilder.name(topicName)
                .build();
    }

    @Bean
    public NewTopic learnWithAnayaTopicJson(){
        // return TopicBuilder.name("learnwithanaya")
        //       .partitions(10).replicas(10).build();

        return TopicBuilder.name(jsonTopicName)
                .build();
    }
}
