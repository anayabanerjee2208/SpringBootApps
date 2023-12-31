package net.learnwithfun.springboot;

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
				title = "Spring Boot Rest API Documentation",
				description = "Spring Boot Rest API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Anaya",
						email = "anaya.banerjee@gmail.com",
						url = "test url"
				),
				license = @License(
						name = "Apache 2.0",
						url = "test url"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot User management documentation",
				url = "test url"
		)
)
public class SpringbootRestfulWebservicesApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}

}
