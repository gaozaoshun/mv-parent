package org.exotic.hystrix;

import org.exotic.service.BasicProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaozs on 2017/10/25.
 */
@Component
public class BasicHystrix implements BasicProvider {

    @Override
    public Map findById(String id) {
        Map map = new HashMap();
        map.put("code",500);
        map.put("msg","fucking you!!!!!");
        return map;
    }
}
