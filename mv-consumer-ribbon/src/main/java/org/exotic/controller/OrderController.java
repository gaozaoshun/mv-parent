package org.exotic.controller;

import org.exotic.dto.OrderModel;
import org.exotic.service.RpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by gaozs on 2017/10/24.
 */
@RestController
public class OrderController {
    @Autowired
    private RpcService rpcService;

    @GetMapping("mv/order/{id}")
    public OrderModel findById(@PathVariable("id") String id){
        return rpcService.findById(id);
    }
}
