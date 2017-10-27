package org.exotic.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.exotic.dto.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by gaozs on 2017/10/25.
 */
@Service
public class RpcService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "findByIdError")
    public OrderModel findById(String id) {
        OrderModel orderModel = restTemplate.getForObject("http://mv-provider-basic/mv/order/"+id,OrderModel.class);
        orderModel.setContent("内容：9999");
        return orderModel;
    }

    public OrderModel findByIdError(String id){
        return new OrderModel();
    }
}
