package net.learnwithanaya.organisationservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrganisationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganisationServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
