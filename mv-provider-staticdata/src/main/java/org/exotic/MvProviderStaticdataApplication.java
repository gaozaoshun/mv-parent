package org.exotic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MvProviderStaticdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvProviderStaticdataApplication.class, args);
	}
}
