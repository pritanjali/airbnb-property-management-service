package com.pritanjalis.airbnb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.IntStream;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan("com.pritanjalis.airbnb.domain")
public class AirbnbPropertyManagementServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbPropertyManagementServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AirbnbPropertyManagementServiceApplication.class);
	}


	//Fake index.html
	@RequestMapping(value = "/")
	public String indexPage() {
		return "AirbnbPropertyManagementServiceApplication is deployed and available";
	}


}
