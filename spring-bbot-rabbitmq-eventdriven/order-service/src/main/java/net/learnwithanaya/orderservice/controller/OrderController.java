package net.learnwithanaya.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.learnwithanaya.orderservice.model.Order;
import net.learnwithanaya.orderservice.model.OrderEvent;
import net.learnwithanaya.orderservice.publisher.EmailProducer;
import net.learnwithanaya.orderservice.publisher.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderController {

    @Autowired
    private OrderProducer orderProducer;
    @Autowired
    private EmailProducer emailProducer;

    @PostMapping("/orders")
    public String sendOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Order is in pending state");
        orderEvent.setStatus("Pending");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);
        emailProducer.sendMessage(orderEvent);
        return "Order sent successfully";
    }
}
