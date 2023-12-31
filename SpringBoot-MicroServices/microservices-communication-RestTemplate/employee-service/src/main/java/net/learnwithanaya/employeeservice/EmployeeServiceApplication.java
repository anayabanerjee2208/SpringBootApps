package net.learnwithanaya.employeeservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmployeeServiceApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Bean
	//RestTemplate class is in maintenance mode and hence Spring team recommended to use WebClient which has supports for sync, async and streaming scenarios
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
