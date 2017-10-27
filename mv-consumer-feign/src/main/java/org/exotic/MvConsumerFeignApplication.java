package org.exotic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MvConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvConsumerFeignApplication.class, args);
	}
}
