package org.exotic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MvEurekaHaServer1Application {

	public static void main(String[] args) {
		SpringApplication.run(MvEurekaHaServer1Application.class, args);
	}
}
