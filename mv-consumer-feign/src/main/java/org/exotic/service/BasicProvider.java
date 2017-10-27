package org.exotic.service;

import org.exotic.hystrix.BasicHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

/**
 * Created by gaozs on 2017/10/25.
 */
@FeignClient(value = "mv-provider-basic",fallback = BasicHystrix.class)
public interface BasicProvider {
    @GetMapping("/mv/order/{id}")
    public Map findById(@PathVariable("id") String id);
}
