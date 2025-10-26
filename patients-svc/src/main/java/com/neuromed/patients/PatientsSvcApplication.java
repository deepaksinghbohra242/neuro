package com.neuromed.patients;

import com.neuromed.patients.dto.PatientsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {PatientsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Patients microservice REST API Documentation",
				description = "Neuromed Patients microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Your Name",
						email = "your.email@neuromed.com",
						url = "https://www.neuromed.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.neuromed.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Neuromed Patients microservice REST API Documentation",
				url = "https://www.neuromed.com/swagger-ui.html"
		)
)
public class PatientsSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsSvcApplication.class, args);
		System.out.println("✅ Patients Service is running...");
	}

}
