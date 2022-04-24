package com.pritanjalis.airbnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.pritanjalis.airbnb.domain")
@EnableDiscoveryClient
public class AirbnbPropertyManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbPropertyManagementServiceApplication.class, args);
	}

}
