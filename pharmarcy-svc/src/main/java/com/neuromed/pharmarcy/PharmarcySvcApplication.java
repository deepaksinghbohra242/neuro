package com.neuromed.pharmarcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Pharmarcy microservice REST API Documentation",
				description = "Neuromed Pharmarcy microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Admin At Neuromed",
						email = "tutor@neuromed.com",
						url = "https://www.neuromed.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.neuromed.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Neuromed Pharmarcy microservice REST API Documentation",
				url = "http://localhost:8085/swagger-ui.html"
		)
)
public class PharmarcySvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmarcySvcApplication.class, args);
	}
}
