package net.learnwithanaya.springboot.kafka.controller;

import net.learnwithanaya.springboot.kafka.Producer.JsonKafkaProducer;
import net.learnwithanaya.springboot.kafka.Producer.KafkaProducer;
import net.learnwithanaya.springboot.kafka.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController {
    private KafkaProducer kafkaProducer;
    private JsonKafkaProducer jsonKafkaProducer;


    @Autowired
    public MessageController(KafkaProducer kafkaProducer, JsonKafkaProducer jsonKafkaProducer) {
        this.kafkaProducer = kafkaProducer;
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @GetMapping("/publish")
    //http://localhost:8080/api/v1/kafka/publish?message=hello world
    public ResponseEntity<String> publish(@RequestParam("message") String message){
            kafkaProducer.sendMessage(message);
            return ResponseEntity.ok("Message sent to the topic");
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("json message sent to kafka topic");
    }
}
