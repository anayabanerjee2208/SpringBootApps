package net.learnwithanaya.employeeservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Employee Service Rest API",
				description = "Employee Service REST Api Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Anaya Banerjee",
						email = "anaya.banerjee@gmail.com",
						url = "Default url"
				),
				license = @License(
						name = "Apache 2.0",
						url = "Default url"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Employee Service Doc",
				url = "Default url"
		)
)
//@EnableFeignClients enables component scanning for interfaces that declare they are Feign Clients
@EnableFeignClients
public class EmployeeServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	//@Bean
	//RestTemplate class is in maintenance mode and hence Spring team recommended to use WebClient which has supports for sync, async and streaming scenarios
	/*public RestTemplate restTemplate(){
		return new RestTemplate();
	}*/

	//WebClient is part of WebFlux dependency
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
