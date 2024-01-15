package net.learnwithanaya.employeeservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
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
