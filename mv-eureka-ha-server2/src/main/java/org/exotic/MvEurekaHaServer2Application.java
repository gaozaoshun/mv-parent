package org.exotic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MvEurekaHaServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(MvEurekaHaServer2Application.class, args);
	}
}
