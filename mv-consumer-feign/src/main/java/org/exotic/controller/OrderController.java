package org.exotic.controller;

import org.exotic.service.BasicProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by gaozs on 2017/10/25.
 */
@RestController
public class OrderController {
    @Autowired
    private BasicProvider basicProvider;

    @GetMapping("/mv/order/{id}")
    public Map detail(@PathVariable("id") String id){
        return basicProvider.findById(id);
    }

}
