package com.luv2code.config;

import com.luv2code.interfaces.Coach;
import com.luv2code.services.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {
    @Bean("aquatic")
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
