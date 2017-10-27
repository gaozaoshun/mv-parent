package org.exotic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MvZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvZuulApplication.class, args);
	}
}
