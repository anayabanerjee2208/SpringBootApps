package com.learn.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.learn")
public class SpringbootTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTutorialApplication.class, args);
	}

}
