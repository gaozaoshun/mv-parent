package org.exotic.controller;

import org.exotic.dto.OrderModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;

/**
 * Created by gaozs on 2017/10/23.
 */
@RestController
public class OrderController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/mv/order/{id}")
    public OrderModel findById(@PathVariable("id") String id){
        OrderModel model = new OrderModel();
        model.setOrderId(id);
        model.setNumber("999");
        model.setPrice(String.valueOf(Math.random()*100));
        model.setSerialNum(UUID.randomUUID().toString());
        model.setPort(port);
        return model;
    }
}
