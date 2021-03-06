package org.exotic.controller;

import org.exotic.service.BasicProvider;
import org.exotic.utils.Response2Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by gaozs on 2017/10/25.
 */
@RefreshScope
@RestController
public class OrderController {
    @Autowired
    private BasicProvider basicProvider;
    @Value("${spring.application.name}")
    private String name;
    @Value("${order.number}")
    private String number;
    @GetMapping("/mv/order/{id}")
    public Response2Json.ResBody detail(@PathVariable("id") String id){
        return Response2Json.toJsonBean(Response2Json.CODE_SUCCESS,number,basicProvider.findById(id));
    }

}
