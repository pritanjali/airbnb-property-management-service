package com.pritanjalis.airbnb.config;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration class to configure Swagger documentation for the application.
 *
 */
@Configuration
@EnableSwagger2
public class AirbnbPropertyManagementSpringFoxConfiguration {

	final Contact contact = new Contact("Pritanjali Singh", "myofficialsite.com", "pritanjali.singh13@gmail.com");
	final ApiInfo apiInfo = new ApiInfoBuilder().title("Airbnb Property Management Service")
			.description("REST endpoints to manage Airbnb Property.").version("1.0")
			.contact(contact).build();
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo);
	}
}

