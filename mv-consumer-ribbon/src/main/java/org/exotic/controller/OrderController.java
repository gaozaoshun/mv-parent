package org.exotic.controller;

import org.exotic.dto.OrderModel;
import org.exotic.service.RpcService;
import org.exotic.utils.Response2Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by gaozs on 2017/10/24.
 */
@RefreshScope
@RestController
public class OrderController {
    @Autowired
    private RpcService rpcService;
    @Value("${msg}")
    private String msg;
    @GetMapping("mv/order/{id}")
    public Response2Json.ResBody findById(@PathVariable("id") String id){
        return Response2Json.toJsonBean(Response2Json.CODE_SUCCESS,msg,rpcService.findById(id));
    }
}
