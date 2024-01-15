package net.learnwithanaya.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Department Service REST API",
				description = "Department Service REST Api Documentation",
				version = "v1.0",
				contact = @Contact(
						email = "anaya.banerjee@gmail.com",
						name = "Anaya Banerjee",
						url = "default"
				),
				license = @License(
						name = "Apache 2.0",
						url = "default"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Department Service Doc",
				url = "default"
		)
)
//Before springboot 3 we had to use @EnableEurekaClient to enable the service as an Eureka Client. But with spring 3 it is not required anymore as it is part of auto config now
public class DepartmentServiceApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DepartmentServiceApplication.class, args);
	}

}
