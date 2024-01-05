package net.learnwithanaya.basedomains.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String orderName;
    private int quantity;
    private double price;
}
