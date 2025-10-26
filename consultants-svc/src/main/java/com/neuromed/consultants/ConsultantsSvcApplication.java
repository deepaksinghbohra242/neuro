package com.neuromed.consultants;

import com.neuromed.consultants.dto.ConsultantsContactInfoDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.neuromed.consultants.service.client")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = { ConsultantsContactInfoDto.class })
@OpenAPIDefinition(
		info = @Info(
				title = "Consultants microservice REST API Documentation",
				description = "Neuromed Consultants microservice REST API Documentation",
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
				description = "Neuromed Consultants microservice REST API Documentation",
				url = "http://localhost:8076/swagger-ui.html"
		)
)
public class ConsultantsSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsultantsSvcApplication.class, args);
	}

}
